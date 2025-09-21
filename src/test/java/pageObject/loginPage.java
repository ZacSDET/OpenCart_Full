package pageObject;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage extends basePage
{

    public loginPage(WebDriver driver)
    {
        super(driver);
    }


    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;
    @FindBy(xpath = "//input[@id='input-password']") WebElement passWord;
    @FindBy(xpath = "//input[@value='Login']") WebElement login;

    public void interEmail(String iEmail)
    {
        email.sendKeys(iEmail);
    }
    public void interPass(String iPass)
    {
        passWord.sendKeys(iPass);
    }
    public void clickLogin()
    {
        login.click();
    }
}
