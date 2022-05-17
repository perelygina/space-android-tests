package com.jetbrains.space.page.search

import com.jetbrains.space.page.Screen
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.qameta.allure.Step
import org.openqa.selenium.WebElement

class SearchScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "search_src_text")
    val searchInputField: WebElement? = null

    @AndroidFindBy(id = "name")
    val resultNames: List<WebElement>? = null

    @Step("Enter the value \"{value}\" in the search bar")
    fun setValueToSearch(value: String) {
        searchInputField!!.sendKeys(value)
    }

    @Step("Open the chat")
    fun openChat(searchResult: WebElement) {
        searchResult.click()
    }
}