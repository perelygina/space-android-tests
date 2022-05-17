package com.jetbrains.space.page

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions.refreshed
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

data class Screen(val driver: AppiumDriver) {

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this)
    }

    fun isDisplayed(element: WebElement, duration: Duration): Boolean {
        return try {
            WebDriverWait(driver, duration).until(refreshed(visibilityOf(element)))
            true
        } catch (e: TimeoutException) {
            false
        }
    }
}