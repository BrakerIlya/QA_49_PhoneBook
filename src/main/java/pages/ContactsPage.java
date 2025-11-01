package pages;

import dto.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class ContactsPage extends BasePage{
    public ContactsPage(WebDriver driver){
        setDriver(driver);

        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);

    }
    @FindBy(xpath = "//a[@href='/contacts']")
    WebElement btnContactsHeader;
    @FindBy(xpath = "//div[@class='contact-page_message__2qafk']")
    WebElement divTextNoContacts;
    @FindBy(className = "contact-item_card__2SOIM")
    List<WebElement> contactList;
    @FindBy(xpath = "//div[@class=\"contact-page_leftdiv__yhyke\"]/div/div[last()]/h2")
    WebElement lastElementList;

    public boolean isTextContactsPresent(String text) {
        return isTextInElementPresent(btnContactsHeader,text);
    }

    public boolean isTextNoContactsPresent(String text) {
        System.out.println(divTextNoContacts.getText());
        return isTextInElementPresent(divTextNoContacts, text);
    }
    public int getNumberOfContacts(){
//        for (WebElement element:contactList){
//            System.out.println(element.getText());
//        }
        return contactList.size();
    }
    public boolean isContactPresent(Contact contact){
        for(WebElement element:contactList){
            if(element.getText().contains(contact.getName())
                    && element.getText().contains(contact.getPhone())){
                System.out.println(element.getText());
                return true;
            }
        }
        return false;
    }
    public void clickLastContact(){
        lastElementList.click();
    }
}
