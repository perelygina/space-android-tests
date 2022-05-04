package com.jetbrains.space

import com.jetbrains.space.page.login.AddNewOrganizationScreen
import com.jetbrains.space.page.login.AppTourScreen
import com.jetbrains.space.page.login.LoginScreen
import com.jetbrains.space.page.login.LoginWebPage
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import io.appium.java_client.service.local.flags.GeneralServerFlag
import io.qameta.allure.Allure
import io.qameta.allure.Step
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.io.ByteArrayInputStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthTest {

    lateinit var service: AppiumDriverLocalService
    lateinit var driver: AppiumDriver

    @BeforeAll
    fun setup() {
        val options = UiAutomator2Options()
            .setDeviceName("name")
            .setApp("src/test/resources/Space.2020.5.6.apk")
            .setAppWaitActivity("circlet.android.ui.workspaces.WorkspacesActivity")

        service = AppiumServiceBuilder()
            .withIPAddress("127.0.0.1")
            .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/")
            .usingPort(4723)
            .build()

        service.start()

        driver = AppiumDriver(service, options)
    }

    @Test
    fun test() {
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

        loginWebPage.loginButton.click()

        Thread.sleep(5000)
        attachment()

        val appTourScreen = AppTourScreen(driver)

        println(driver.pageSource)

        appTourScreen.skipTourButton.click()

        appTourScreen.todoButton.click()
    }

    @AfterAll
    fun after() {
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