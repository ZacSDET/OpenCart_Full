package testCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.accountRegistrationPage;
import pageObject.homePage;
import testBase.baseClass;

public class TC001_AccountRegistrationTest extends baseClass
{

    @Test(groups = {"Regression","Master"})
  public   void verify_Account_Registration()

  {
      logger.info("*** starting test TC001_AccountRegistrationTest ***");
      try {
          homePage hp = new homePage(driver);
          hp.clickMyAccount();
          hp.clickRegister();
          accountRegistrationPage ar = new accountRegistrationPage(driver);
          ar.setFirstName(rondomString());
          ar.setLastName(rondomString());
          ar.setEmail(rondomString() + "@gmail.com");
          ar.setTelephone(randomNumber());
          logger.info("***********########************");
          String passWord = randoAlphaNumeric();
          logger.info("*****set telephone number*****");
          ar.setPass(passWord);
          ar.confirmPass(passWord);
          ar.clickPolicy();
          ar.continueClick();
          String confirmation_msg = ar.getConfirmationMsg();
          Assert.assertEquals(confirmation_msg, "Your Account Has Been Created!");
      } catch (Exception e)

      {
          logger.error("Test failed ");
          logger.debug("Debug logs");
          Assert.fail();
      }

  }
}
