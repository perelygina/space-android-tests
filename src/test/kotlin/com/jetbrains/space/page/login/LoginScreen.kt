package com.jetbrains.space.page.login

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class LoginScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "onboardingHeader")
    lateinit var onboardingHeader: WebElement

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in to your organization']")
    lateinit var header: WebElement

    @AndroidFindBy(id = "addNewOrganization")
    lateinit var addNewOrganizationButton: WebElement
}