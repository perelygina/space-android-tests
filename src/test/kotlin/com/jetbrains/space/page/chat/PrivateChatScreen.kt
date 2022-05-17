package com.jetbrains.space.page.chat

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.qameta.allure.Step
import org.openqa.selenium.WebElement

class PrivateChatScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "chat_screen_toolbar_text")
    val userNameText: WebElement? = null

    @AndroidFindBy(id = "chat_new_message_text_input")
    val messageInputField: WebElement? = null

    @AndroidFindBy(id = "chat_new_message_send_filled")
    val sendMessageButton: WebElement? = null

    @AndroidFindBy(id = "chat_message_content_text_text")
    val messageTexts: List<WebElement>? = null

    @Step("Send a message with the text \"{value}\"")
    fun sendTextMessage(value: String) {
        messageInputField!!.sendKeys(value)
        sendMessageButton!!.click()
    }
}