package com.jetbrains.space.page.login

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class AddNewOrganizationScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "back")
    lateinit var returnToListButton: WebElement

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Organization URL']")
    lateinit var orgUrlLabel: WebElement

    @AndroidFindBy(id = "workspaceAddress")
    lateinit var orgUrlInput: WebElement

    @AndroidFindBy(id="manualLogIn")
    lateinit var logInButton: WebElement
}