package com.jetbrains.space.util

import io.appium.java_client.AppiumDriver
import io.qameta.allure.Allure
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import java.io.ByteArrayInputStream

data class Screenshoter(val driver: AppiumDriver) : TestWatcher {

    override fun testFailed(context: ExtensionContext?, cause: Throwable?) {
        makeScreenshot("Failure screenshot")
        super.testFailed(context, cause)
    }

    fun makeScreenshot(name: String) {
        Allure.addAttachment(
            name,
            ByteArrayInputStream((driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES))
        )
    }
}