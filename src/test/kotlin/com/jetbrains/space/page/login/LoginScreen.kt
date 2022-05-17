package com.jetbrains.space.page.login

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class LoginScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "onboardingHeader")
    val onboardingHeader: WebElement? = null

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in to your organization']")
    val header: WebElement? = null

    @AndroidFindBy(id = "addNewOrganization")
    val addNewOrganizationButton: WebElement? = null
}