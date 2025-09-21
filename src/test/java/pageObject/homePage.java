package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage
{
    public homePage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;
    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
    WebElement Register;
    @FindBy(xpath = "//a[normalize-space()='Login']") WebElement homelogin;


    @FindBy(xpath ="//i[@class='fa fa-user']")WebElement accountSingn;

    public void clickMyAccount()
    {
        myAccount.click();
    }
    public void clickRegister()
    {
        Register.click();
    }
    public void clickLoginHome()
    {
        homelogin.click();
    }
    public void clickAccountSign()
    {
        accountSingn.click();
    }

}
