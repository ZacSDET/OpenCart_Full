package pageObject;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class accountRegistrationPage extends basePage
{
    public accountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telephone;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passWord;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPass;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement policyAgree;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continueClick;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;
    ////div[@id='content']//h1
    //

    public void setFirstName(String fname)
    {
        firstName.sendKeys(fname);
    }
    public void setLastName(String lname)
    {
        lastName.sendKeys(lname);
    }
    public void setEmail(String emaila)
    {
        email.sendKeys(emaila);
    }
    public void setTelephone(String tnumber)
    {
        telephone.sendKeys(tnumber);
    }
    public void setPass(String pwd)
    {
        passWord.sendKeys(pwd);
    }
    public void confirmPass(String confirmPwd)
    {
        confirmPass.sendKeys(confirmPwd);
    }
    public void clickPolicy()
    {
        policyAgree.click();
    }
    public void continueClick()
    {
        continueClick.click();
    }
    public String getConfirmationMsg()
    {
        try {
            return (msgConfirmation.getText());
        }catch (Exception e){
            return (e.getMessage());
        }

    }
}
