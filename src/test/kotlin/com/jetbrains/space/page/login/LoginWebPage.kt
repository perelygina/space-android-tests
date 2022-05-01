package com.jetbrains.space.page.login

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import javax.swing.AbstractButton

class LoginWebPage(driver: AppiumDriver) : Screen(driver) {

    @FindBy(xpath = "//android.webkit.WebView//android.widget.EditText[1]")
    lateinit var usernameInput: WebElement

    @FindBy(xpath = "//android.webkit.WebView//android.widget.EditText[2]")
    lateinit var usernamePassword: WebElement

    @FindBy(xpath = "//android.webkit.WebView//android.widget.Button")
    lateinit var loginButton: WebElement

    // уже другая форма, лучше бы разделить
    @FindBy(xpath = "//android.webkit.WebView//android.widget.Button[2]")
    lateinit var acceptButton: WebElement
}