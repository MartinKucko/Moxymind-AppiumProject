package tests;

import base.AndroidBaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static base.FunctionsBase.getJsonData;

public class CreateContactTest extends AndroidBaseTest {

    @BeforeMethod()
    public void preSetup() {
        listContactsPage.setActivity();
    }

    @Test(dataProvider = "getData")
    public void CreateContact(HashMap<String, String> input) {
        listContactsPage.searchButtonControl();
        listContactsPage.clickCreateContactButton();
        createContactPage.createContact(input.get("firstName"), input.get("lastName"), input.get("company"), input.get("phone"), input.get("email"));
        createContactPage.clickSaveContactButton();
        detailContactPage.controlCreatedContactDetail(input.get("firstName"), input.get("lastName"), input.get("company"), input.get("phone"), input.get("email"));
        detailContactPage.clickNavigateUpButton();
        listContactsPage.controlCreatedContactOnList(input.get("firstName"), input.get("lastName"));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "//src//test//java//testData//contactsList1.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
