package com.jetbrains.space.page

import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.Widget
import org.openqa.selenium.WebElement

@AndroidFindBy(id = "bottomNavigationView")
class BottomNavBar(element: WebElement): Widget(element) {

    @AndroidFindBy(id = "chats")
    lateinit var chatsButton: WebElement;

    @AndroidFindBy(id = "gotoany")
    lateinit var searchButton: WebElement;

    @AndroidFindBy(id = "todo")
    lateinit var todoButton: WebElement;

    @AndroidFindBy(id = "dashboard")
    lateinit var dashboardButton: WebElement;
}