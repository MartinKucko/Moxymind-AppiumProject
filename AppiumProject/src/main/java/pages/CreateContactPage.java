package pages;

import base.FunctionsBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CreateContactPage extends FunctionsBase {

    AndroidDriver driver;

    public CreateContactPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Create contact']")
    private WebElement createContactTitle;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='First name']")
    private WebElement contactFirstNameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Last name']")
    private WebElement contactLastNameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Company']")
    private WebElement contactCompanyField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
    private WebElement contactPhoneField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Email']")
    private WebElement contactEmailField;

    @AndroidFindBy(id = "com.google.android.contacts:id/toolbar_button")
    private WebElement saveContactButton;

    public void createContact(String firstName, String lastName, String company, String phone, String email) {
        Assert.assertTrue(createContactTitle.isDisplayed());
        contactFirstNameField.sendKeys(firstName);
        contactLastNameField.sendKeys(lastName);
        contactCompanyField.sendKeys(company);
        contactPhoneField.sendKeys(phone);
        scrollToText("More fields");
        contactEmailField.sendKeys(email);
    }

    public void clickSaveContactButton() {
        saveContactButton.click();
    }
}
