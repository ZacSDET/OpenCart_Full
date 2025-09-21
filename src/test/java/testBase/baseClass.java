package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class baseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity","Regression","Master",})
    @Parameters({"os","browser"})
    public void setUp(String os, String br) throws IOException {


        FileInputStream file=new FileInputStream("./src/config.properties");
        p=new Properties();
        p.load(file);
        logger=LogManager.getLogger(this.getClass());

        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities =new DesiredCapabilities();

            if(os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN11);
            }else if (os.equalsIgnoreCase("Linux"))
            {
                capabilities.setPlatform(Platform.LINUX);

            }else
            {
                System.out.println("No matching os");
            }
            switch (br.toLowerCase())
            {
                case "chrome":capabilities.setBrowserName("chrome");break;
                case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
                case "firefox":capabilities.setBrowserName("firefox");break;
                default: System.out.println("Invalid Browser");return;
            }
            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);


        }

        if(p.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            switch (br.toLowerCase())
            {
                case "chrome":  driver = new ChromeDriver();break;
                case  "edge": driver=new EdgeDriver();break;
                case "firefox": driver=new FirefoxDriver();break;
                default: System.out.println("invalid browser"); return;
            }
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
        driver.get(p.getProperty("URL"));
        driver.manage().window().maximize();
    }
    public String rondomString()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
    public String randomNumber()
    {
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }
    public String randoAlphaNumeric()
    {
        String generateString = RandomStringUtils.randomNumeric(5);
        String generatedNumber = RandomStringUtils.randomAlphabetic(5);

        return generatedNumber + "#" + generateString;
    }
    public String captureScreen(String tName)
    {
     String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot screenshot =  (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath=System.getProperty("user.dir")+"/screenShots"+ tName + "_"+ timeStamp+".png";
        File targetFile=new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;

    }
    @AfterClass(groups = {"Sanity","Regression","Master",})
    public void tearDown()
    {
        driver.quit();
    }
}
