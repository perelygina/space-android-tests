package com.jetbrains.space

import com.jetbrains.space.page.login.*
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import io.appium.java_client.service.local.flags.GeneralServerFlag
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

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

        val appTourScreen = AppTourScreen(driver)
        appTourScreen.skipTourButton.click()

        appTourScreen.todoButton.click()
    }

    @AfterAll
    fun after() {
        service.stop()
    }
}