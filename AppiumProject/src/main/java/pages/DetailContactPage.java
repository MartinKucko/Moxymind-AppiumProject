package pages;

import base.FunctionsBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DetailContactPage extends FunctionsBase {

    AndroidDriver driver;

    public DetailContactPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
    }

    @AndroidFindBy(accessibility = "Navigate up")
    private WebElement navigateUpButton;

    @AndroidFindBy(id = "android:id/closeButton")
    private WebElement closePopupButton;

    @AndroidFindBy(id = "com.google.android.contacts:id/menu_insert_or_edit")
    private WebElement editContactButton;


    private WebElement contactDetailTextView(String text) {
        String xpath = "//android.widget.TextView[@text='" + text + "']";
        return driver.findElement(By.xpath(xpath));
    }

    public void closePopupButton() {
        try {
            if (closePopupButton.isDisplayed()) {
                closePopupButton.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Popup button not found");
        }
    }

    public void clickNavigateUpButton() {
        navigateUpButton.click();
    }

    public void controlCreatedContactDetail(String firstName, String lastName, String company, String phone, String email) {
        Assert.assertTrue(editContactButton.isDisplayed());
        closePopupButton();
        Assert.assertTrue(contactDetailTextView(firstName + " " + lastName).isDisplayed(), "Contact name does not match!");
        Assert.assertTrue(contactDetailTextView(company).isDisplayed(), "Company does not match!");
        Assert.assertTrue(contactDetailTextView(phone).isDisplayed(), "Phone does not match!");
        Assert.assertTrue(contactDetailTextView(email).isDisplayed(), "Email does not match!");
    }
}
