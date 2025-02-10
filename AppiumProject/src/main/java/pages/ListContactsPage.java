package pages;

import base.FunctionsBase;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ListContactsPage extends FunctionsBase {

    AndroidDriver driver;

    public ListContactsPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
    }

    @AndroidFindBy(id = "android:id/text1")
    private WebElement noContactsMessage;

    @AndroidFindBy(id = "com.google.android.contacts:id/floating_action_button")
    private WebElement createContactButton;

    @AndroidFindBy(accessibility = "Search")
    private WebElement searchContactButton;

    @AndroidFindBy(id = "com.google.android.contacts:id/menu_delete")
    private WebElement deleteContactButton;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement confirmationOfDeletion;


    public void setActivity() {
        driver.executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "com.google.android.contacts/com.android.contacts.activities.PeopleActivity"));
    }

    public void clickCreateContactButton() {
        createContactButton.click();
    }

    public void searchButtonControl() {
        Assert.assertTrue(searchContactButton.isDisplayed());
    }

    private WebElement contactTextView(String text) {
        String xpath = "//android.widget.TextView[@text='" + text + "']";
        return driver.findElement(By.xpath(xpath));
    }

    public void controlCreatedContactOnList(String firstName, String lastName) {
        Assert.assertTrue(contactTextView(firstName + " " + lastName).isDisplayed(), "Contact name does not match!");

    }

    public void noContactsMessage() {
        Assert.assertTrue(noContactsMessage.isDisplayed());
    }

    public void deleteAllContacts() {
        List<WebElement> contactsList = driver.findElements(
                By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.view.ViewGroup")
        );

        if (contactsList.isEmpty()) {
            return;
        }

        while (true) {
            try {
                WebElement firstContact = driver.findElement(
                        By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.view.ViewGroup[1]")
                );

                longPressAction(firstContact);
                deleteContactButton.click();
                confirmationOfDeletion.click();
                List<WebElement> remainingContacts = driver.findElements(
                        By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.view.ViewGroup")
                );

                if (remainingContacts.isEmpty()) {
                    break;
                }

            } catch (Exception e) {
                System.out.println("An error occurred while deleting the contact: " + e.getMessage());
                break;
            }
        }
    }
}
