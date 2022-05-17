package com.jetbrains.space.page.login

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.qameta.allure.Step
import org.openqa.selenium.WebElement

class AddNewOrganizationScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "back")
    val returnToListButton: WebElement? = null

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Organization URL']")
    val orgUrlLabel: WebElement? = null

    @AndroidFindBy(id = "workspaceAddress")
    val orgUrlInput: WebElement? = null

    @AndroidFindBy(id = "manualLogIn")
    val logInButton: WebElement? = null

    @Step("Enter the name of the workspace \"{value}\"")
    fun setWorkspaceName(value: String) {
        orgUrlInput!!.sendKeys(value)
        logInButton!!.click()
    }
}