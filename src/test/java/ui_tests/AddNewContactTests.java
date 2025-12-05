package ui_tests;

import data_providers.Contact_DP;
import dto.Contact;
import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.ContactFactory;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static utils.PropertiesReader.*;

import static pages.BasePage.*;
@Listeners(TestNGListener.class)
public class AddNewContactTests extends ApplicationManager {
    SoftAssert softAssert=new SoftAssert();
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;
    int numberOfContacts;

    @BeforeMethod(alwaysRun = true)
    public void login(){
        homePage=new HomePage(getDriver());
        loginPage= clickButtonHeader(HeaderMenuItem.LOGIN);
//        User user =new User("a@mail.ru","Password123!");
//        loginPage.typeLoginFormWithUser(user);
        loginPage.typeLoginForm(getProperty("base.properties","login")
                ,getProperty("base.properties","password"));
        contactsPage=new ContactsPage(getDriver());
        numberOfContacts=contactsPage.getNumberOfContacts();
        addPage=clickButtonHeader(HeaderMenuItem.ADD);
    }
    @Test(groups = {"smoke","contact"})
    public void addNewContactPositiveTest(){
        addPage.typeContactForm(ContactFactory.positiveContact());
        int numberOfContactsAfterAdd=contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd,numberOfContacts+1);
    }
    @Test(dataProvider = "dataProviderContactFile", dataProviderClass = Contact_DP.class)
    public void addNewContactPositiveTestWithDataProvider(Contact contact){
        addPage.typeContactForm(contact);
        int numberOfContactsAfterAdd=contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd,numberOfContacts+1);
    }
    @Test
    public void addNewContactPositiveTestValidateList(){
        Contact contact=ContactFactory.positiveContact();
        addPage.typeContactForm(contact);
//        contactsPage.clickLastContact();
        Assert.assertTrue(contactsPage.isContactPresent(contact));
    }
    @Test(groups = "negative")
    public void addNewContactPositiveTest_validateElementSCROLL(){
        Contact contact=ContactFactory.positiveContact();
        addPage.typeContactForm(contact);
        contactsPage.scrollToLastElementList();
//        contactsPage.scrollToLastElementListJS();
        contactsPage.clickLastContact();
        String text= contactsPage.getContactCardText();
        softAssert.assertTrue(text.contains(contact.getName()));
        softAssert.assertTrue(text.contains(contact.getLastName()));
        softAssert.assertTrue(text.contains("zzzzzzzzzz"),"message contains Phone");
        softAssert.assertTrue(text.contains(contact.getEmail()));
        softAssert.assertTrue(text.contains(contact.getAddress()));
        softAssert.assertAll();
    }
}
