package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.homePage;
import pageObject.loginPage;
import pageObject.myAccountPage;
import testBase.baseClass;
import utilities.dataProvider;

public class TC003LoginDDT extends baseClass
{
    @Test(dataProvider = "LoginData",dataProviderClass = dataProvider.class,groups = "DataDriven")
    public void verifyLoginDDT(String email, String pwd, String expD)
    {
        logger.info("***Started TC003");
        try {
            homePage homePage = new homePage(driver);
            //homePage.clickRegister();
           //homePage.clickMyAccount();
            homePage.clickMyAccount();
            homePage.clickLoginHome();

            loginPage loginPage = new loginPage(driver);
            loginPage.interEmail(email);
            loginPage.interPass(pwd);
            loginPage.clickLogin();
            myAccountPage myAccountPage = new myAccountPage(driver);
            boolean myacount = myAccountPage.isMyAccountPageExists();

            if (expD.equalsIgnoreCase("valid")) {
                if (myacount == true) {
                    myAccountPage.clickLogOut();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (expD.equalsIgnoreCase("invalid"))

                if (myacount == true) {
                    myAccountPage.clickLogOut();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
        }catch (Exception e)
        {
            Assert.fail();
        }


        logger.info("###### TC003 finished ######");

    }
}
