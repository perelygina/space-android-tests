package com.jetbrains.space.page.login

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.qameta.allure.Step
import org.openqa.selenium.WebElement

class AppTourScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "pageButton")
    val skipTourButton: WebElement? = null

    @Step("Skip tour")
    fun skipTour() {
        skipTourButton!!.click()
    }
}