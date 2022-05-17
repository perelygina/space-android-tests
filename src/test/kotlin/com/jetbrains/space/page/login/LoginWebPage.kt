package com.jetbrains.space.page.login

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.qameta.allure.Step
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class LoginWebPage(driver: AppiumDriver) : Screen(driver) {

    @FindBy(xpath = "//android.webkit.WebView//android.widget.EditText[1]")
    val usernameInput: WebElement? = null

    @FindBy(xpath = "//android.webkit.WebView//android.widget.EditText[2]")
    val usernamePassword: WebElement? = null

    @FindBy(xpath = "//android.widget.Button[@text=\"Log in\"]")
    val loginButton: WebElement? = null

    @FindBy(xpath = "//android.widget.Button[@text=\"Accept\"]")
    val acceptButton: WebElement? = null

    @FindBy(xpath = "//android.widget.Button[@text=\"Open the app\"]")
    val openAppButton: WebElement? = null

    @Step("Enter the user login and password")
    fun setUserLoginAndPassword(username: String, password: String) {
        usernameInput!!.sendKeys(username)
        usernamePassword!!.sendKeys(password)
        loginButton!!.click()
        acceptButton!!.click()
        openAppButton!!.click()
    }
}