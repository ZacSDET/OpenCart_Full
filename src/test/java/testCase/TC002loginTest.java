package testCase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.homePage;
import pageObject.loginPage;
import pageObject.myAccountPage;
import testBase.baseClass;

import java.util.logging.Logger;

public class TC002loginTest extends baseClass
{
    @Test(groups = {"Sanity","Master"})
    public void verifyLogin()
    {
        logger.info("***Started TC002");
        try {
            homePage homePage = new homePage(driver);
            //homePage.clickRegister();
            homePage.clickMyAccount();
            homePage.clickLoginHome();

            loginPage loginPage = new loginPage(driver);
            loginPage.interEmail(p.getProperty("Email"));
            loginPage.interPass(p.getProperty("passWord"));
            loginPage.clickLogin();
            myAccountPage myAccountPage = new myAccountPage(driver);
            boolean myacount = myAccountPage.isMyAccountPageExists();

            Assert.assertTrue(myacount);
        }catch (Exception e){

            Assert.fail();
        }

        logger.info("###### TC002 finished ######");

    }
}
