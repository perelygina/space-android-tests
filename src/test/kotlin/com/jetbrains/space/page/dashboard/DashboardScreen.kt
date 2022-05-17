package com.jetbrains.space.page.dashboard

import com.jetbrains.space.page.Screen
import com.jetbrains.space.page.widget.BottomNavBar
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.qameta.allure.Step
import org.openqa.selenium.WebElement

class DashboardScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "userName")
    lateinit var userNameText: WebElement

    @AndroidFindBy(accessibility = "Ещё")
    lateinit var moreButton: WebElement

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Add Absence\"]")
    lateinit var addAbsenceButton: WebElement

    @AndroidFindBy(id = "absencesTitle")
    val title: WebElement? = null

    @AndroidFindBy(id = "summary")
    val summaries: List<WebElement>? = null

    @AndroidFindBy(id = "date")
    val dates: List<WebElement>? = null

    lateinit var bottomNavBar: BottomNavBar

    @Step("Open 3-Dots menu")
    fun open3DotsMenu() {
        moreButton.click()
    }

    @Step("Tap on add absence button")
    fun tapOnAddAbsenceButton() {
        addAbsenceButton.click()
    }
}