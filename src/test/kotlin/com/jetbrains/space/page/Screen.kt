package com.jetbrains.space.page

import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.support.PageFactory
import java.time.Duration

abstract class Screen(driver: AppiumDriver) {

    init {
        PageFactory.initElements(AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this)
    }
}