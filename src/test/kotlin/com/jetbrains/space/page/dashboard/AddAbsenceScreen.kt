package com.jetbrains.space.page.dashboard

import com.jetbrains.space.page.Screen
import com.jetbrains.space.page.widget.BottomNavBar
import io.appium.java_client.AppiumDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import io.qameta.allure.Step
import org.openqa.selenium.WebElement

class AddAbsenceScreen(driver: AppiumDriver) : Screen(driver) {

    @AndroidFindBy(id = "member_absence_reason_item_text")
    val firstReasonText: WebElement? = null

    @AndroidFindBy(id = "member_absence_add_description_edit_text")
    val descriptionEditText: WebElement? = null

    @AndroidFindBy(
        xpath = "//*[contains(@resource-id,\"member_absence_add_date_start\")]" +
                "/*[contains(@resource-id,\"widget_labeled_dropdown_button_the_button\")]"
    )
    val startDateDropdownButton: WebElement? = null

    @AndroidFindBy(
        xpath = "//*[contains(@resource-id,\"member_absence_add_date_end\")]" +
                "/*[contains(@resource-id,\"widget_labeled_dropdown_button_the_button\")]"
    )
    val endDateDropdownButton: WebElement? = null

    @AndroidFindBy(id = "member_absence_add_available_switch")
    val availabilityToggle: WebElement? = null

    @AndroidFindBy(id = "member_absence_submit")
    val addAbsenceButton: WebElement? = null

    @AndroidFindBy(xpath = "//*[@text=\"Absence successfully added!\"]")
    val successMessage: WebElement? = null

    val bottomNavBar: BottomNavBar? = null

    @Step("Enter value {value} in the description field")
    fun setDescription(value: String) {
        descriptionEditText!!.sendKeys(value)
    }

    @Step("Set value {value} to the availability toggle")
    fun changeAvailabilityToggleValue(value: Boolean) {
        val currentValue = availabilityToggle!!.getAttribute("checked").toBoolean()
        if (value != currentValue) {
            availabilityToggle.click()
        }
    }
}