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
import com.jetbrains.space.util.PropertiesReader
import com.jetbrains.space.util.PropertyConstants.Companion.APPIUM_BASE_PATH
import com.jetbrains.space.util.PropertyConstants.Companion.APPIUM_IP
import com.jetbrains.space.util.PropertyConstants.Companion.APP_DOWNLOAD_LINK
import com.jetbrains.space.util.PropertyConstants.Companion.APP_FILE_NAME
import com.jetbrains.space.util.PropertyConstants.Companion.APP_WAIT_ACTIVITY
import com.jetbrains.space.util.PropertyConstants.Companion.DEVICE_NAME
import com.jetbrains.space.util.PropertyConstants.Companion.USER_NAME
import com.jetbrains.space.util.PropertyConstants.Companion.USER_PASSWORD
import com.jetbrains.space.util.PropertyConstants.Companion.WORKSPACE_NAME
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import io.appium.java_client.service.local.flags.GeneralServerFlag
import io.qameta.allure.Allure
import io.qameta.allure.Step
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import java.io.ByteArrayInputStream
import java.io.File
import java.lang.String.format
import java.net.URL

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class AuthTest {

    private val propertiesReader = PropertiesReader()

    lateinit var service: AppiumDriverLocalService
    lateinit var driver: AppiumDriver

    private val REASON_DEFAULT_NAME = "Business Trip"
    private val CUSTOM_DESCRIPTION = "My description"

    @BeforeAll
    fun setup() {
        FileUtils.copyURLToFile(
            URL(propertiesReader.getProperty(APP_DOWNLOAD_LINK)),
            File(propertiesReader.getProperty(APP_FILE_NAME))
        )

        val options = UiAutomator2Options()
            .setDeviceName(propertiesReader.getProperty(DEVICE_NAME))
            .setApp(propertiesReader.getProperty(APP_FILE_NAME))
            .setAppWaitActivity(propertiesReader.getProperty(APP_WAIT_ACTIVITY))

        service = AppiumServiceBuilder()
            .withIPAddress(propertiesReader.getProperty(APPIUM_IP))
            .withArgument(GeneralServerFlag.BASEPATH, propertiesReader.getProperty(APPIUM_BASE_PATH))
            .usingAnyFreePort()
            .build()

        service.start()

        driver = AppiumDriver(service, options)
        login()
    }

    fun login() {
        val loginScreen = LoginScreen(driver)
        loginScreen.addNewOrganizationButton.click()

        val addNewOrganizationScreen = AddNewOrganizationScreen(driver)
        addNewOrganizationScreen.orgUrlInput.sendKeys(propertiesReader.getProperty(WORKSPACE_NAME))
        addNewOrganizationScreen.logInButton.click()

        val loginWebPage = LoginWebPage(driver)
        loginWebPage.usernameInput.sendKeys(propertiesReader.getProperty(USER_NAME))
        loginWebPage.usernamePassword.sendKeys(propertiesReader.getProperty(USER_PASSWORD))
        loginWebPage.loginButton.click()

        loginWebPage.acceptButton.click()

        loginWebPage.loginButton.click()

        attachment()

        val appTourScreen = AppTourScreen(driver)
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