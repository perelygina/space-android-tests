package com.jetbrains.space.test

import com.jetbrains.space.page.chat.ChatsScreen
import com.jetbrains.space.page.chat.PrivateChatScreen
import com.jetbrains.space.page.dashboard.AddAbsenceScreen
import com.jetbrains.space.page.dashboard.DashboardScreen
import com.jetbrains.space.page.login.AddNewOrganizationScreen
import com.jetbrains.space.page.login.AppTourScreen
import com.jetbrains.space.page.login.LoginScreen
import com.jetbrains.space.page.login.LoginWebPage
import com.jetbrains.space.page.search.SearchScreen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import io.appium.java_client.service.local.flags.GeneralServerFlag
import io.qameta.allure.Allure
import io.qameta.allure.Step
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import java.io.ByteArrayInputStream
import java.lang.String.format

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class AuthTest {

    lateinit var service: AppiumDriverLocalService
    lateinit var driver: AppiumDriver

    private val REASON_DEFAULT_NAME = "Business Trip"
    private val CUSTOM_DESCRIPTION = "My description"

    @BeforeAll
    fun setup() {
        val options = UiAutomator2Options()
            .setDeviceName("name")
            .setApp("src/test/resources/Space.2020.5.6.apk")
            .setAppWaitActivity("circlet.android.ui.workspaces.WorkspacesActivity")
//            .noReset()

        service = AppiumServiceBuilder()
            .withIPAddress("127.0.0.1")
            .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/")
            .usingAnyFreePort()
            .build()

        service.start()

        driver = AppiumDriver(service, options)
        login()
    }

    fun login() {
//        val welcomeScreen = WelcomeScreen(driver)
//        assertNotNull(welcomeScreen.header.text)

        val loginScreen = LoginScreen(driver)
        loginScreen.addNewOrganizationButton.click()

        val addNewOrganizationScreen = AddNewOrganizationScreen(driver)
        addNewOrganizationScreen.orgUrlInput.sendKeys("aperelygina.jetbrains.space")
        addNewOrganizationScreen.logInButton.click()

        val loginWebPage = LoginWebPage(driver)
        // Нужно управлять кэшом браузера чтобы было понятно состояние
        loginWebPage.usernameInput.sendKeys("a.perelygina")
        loginWebPage.usernamePassword.sendKeys("84.pNRznzbXf!Hb")
        loginWebPage.loginButton.click()

        loginWebPage.acceptButton.click()

        loginWebPage.loginButton.click() //??

        Thread.sleep(5000)
        attachment()

        val appTourScreen = AppTourScreen(driver)

        println(driver.pageSource)

        appTourScreen.skipTourButton.click()

        appTourScreen.todoButton.click()
    }

    @Test
    @DisplayName("Add new absence")
    @Order(1)
    fun addAbsence() {
        val chatsScreen = ChatsScreen(driver)
        chatsScreen.bottomNavBar.navigateToDashboard()

        val dashboardScreen = DashboardScreen(driver)
        dashboardScreen.open3DotsMenu()
        dashboardScreen.tapOnAddAbsenceButton()

        val addAbsenceScreen = AddAbsenceScreen(driver)

        assertEquals(REASON_DEFAULT_NAME, addAbsenceScreen.firstReasonText.text)
        assertEquals(REASON_DEFAULT_NAME, addAbsenceScreen.descriptionEditText.text)

        addAbsenceScreen.setDescription(CUSTOM_DESCRIPTION)

        val startDate = addAbsenceScreen.startDateDropdownButton.text
        val endDate = addAbsenceScreen.endDateDropdownButton.text

        assertEquals(startDate, endDate)

        addAbsenceScreen.changeAvailabilityToggleValue(false)
        addAbsenceScreen.addAbsenceButton.click()

//        assertTrue(
//            addAbsenceScreen.isDisplayed(addAbsenceScreen.successMessage, ofMillis(1000)),
//            "Success message is not displayed"
//        )

        driver.navigate().back()

        val dashboardScreenAfterAddingAbsence = DashboardScreen(driver)

        val lastCreatedAbsenceSummary = dashboardScreenAfterAddingAbsence.summaries!!.first().text
        val lastCreatedAbsenceDate = dashboardScreenAfterAddingAbsence.dates!!.first().text

        assertEquals(format("%s, %s", REASON_DEFAULT_NAME, CUSTOM_DESCRIPTION), lastCreatedAbsenceSummary)
        assertTrue(startDate.contains(lastCreatedAbsenceDate))
    }


    @Test
    @DisplayName("")
    @Order(2)
    fun searchUserByName() {
        val VALUE_TO_SEARCH = "Anastasiya Perelygina"

        val chatsScreen = ChatsScreen(driver)
        chatsScreen.bottomNavBar.navigateToSearch()

        val searchScreen = SearchScreen(driver)
        searchScreen.setValueToSearch(VALUE_TO_SEARCH)
        val searchResult = searchScreen.resultNames!!.first { e -> VALUE_TO_SEARCH == e.text }
        assertNotNull(searchResult)
        searchScreen.openChat(searchResult)

        val privateChatScreen = PrivateChatScreen(driver)
        assertEquals(VALUE_TO_SEARCH, privateChatScreen.userNameText!!.text)
    }

    @Test
    @DisplayName("")
    @Order(3)
    fun sendMessageToYourself() {
        val MESSAGE_TEXT = "my message"
        val privateChatScreen = PrivateChatScreen(driver)
        privateChatScreen.sendTextMessage(MESSAGE_TEXT)
        assertEquals(MESSAGE_TEXT, privateChatScreen.messageTexts!!.last().text)
    }

    @AfterAll
    fun after() {
        attachment()
        driver.quit()
        service.stop()
    }

    @Step
    fun attachment() {
        Allure.addAttachment(
            "Any text",
            ByteArrayInputStream((driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES))
        )
    }
}