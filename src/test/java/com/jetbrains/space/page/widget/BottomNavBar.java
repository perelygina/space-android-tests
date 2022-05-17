package com.jetbrains.space.page.widget;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

@AndroidFindBy(id = "bottomNavigationView")
public class BottomNavBar extends Widget {

    @AndroidFindBy(id = "chats")
    private WebElement chatsButton;

    @AndroidFindBy(id = "gotoany")
    private WebElement searchButton;

    @AndroidFindBy(id = "todo")
    private WebElement todoButton;

    @AndroidFindBy(id = "dashboard")
    private WebElement dashboardButton;

    protected BottomNavBar(WebElement element) {
        super(element);
    }

    @Step("Navigate to chats")
    public void navigateToChats() {
        chatsButton.click();
    }

    @Step("Navigate to searsh")
    public void navigateToSearch() {
        searchButton.click();
    }

    @Step("Navigate to todo")
    public void navigateToTodo() {
        todoButton.click();
    }

    @Step("Navigate to dashboard")
    public void navigateToDashboard() {
        dashboardButton.click();
    }
}
