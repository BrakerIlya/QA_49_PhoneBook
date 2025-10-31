package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.HeaderMenuItem;

import static pages.BasePage.*;

public class AddNewContactTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;

    @BeforeMethod
    public void login(){
        homePage=new HomePage(getDriver());
        loginPage= clickButtonHeader(HeaderMenuItem.LOGIN);
        User user =new User("a@mail.ru","Password123!");
        loginPage.typeLoginFormWithUser(user);
        contactsPage=new ContactsPage(getDriver());
        addPage=clickButtonHeader(HeaderMenuItem.ADD);
    }
    @Test
    public void addNewContactPositiveTest(){

    }
}
