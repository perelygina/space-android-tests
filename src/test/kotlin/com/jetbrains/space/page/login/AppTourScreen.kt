package com.jetbrains.space.page.login

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class AppTourScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "pageButton")
    lateinit var skipTourButton: WebElement

    @AndroidFindBy(id = "todo")
    lateinit var todoButton: WebElement
}