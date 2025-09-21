package utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.baseClass;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;

    public void onStart(ITestContext testContext) {

        /*SimpleDateFormat sd=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date dt=new Date();
        String currentDateTimeStamp=sd.format(dt);
         */
        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName="Test-Report- "+timeStamp+".html";
        sparkReporter=new ExtentSparkReporter("./reports/"+repName);
        sparkReporter.config().setDocumentTitle("opencart automation report");
        sparkReporter.config().setReportName("opencart functional testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application","Opencart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System",os);


        String browser= testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser",browser);

        List<String>includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty())
        {
            extent.setSystemInfo("Groups",includedGroups.toString());
        }
    }


    public void onTestSuccess(ITestResult result) {
        test=extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,result.getName()+" successfully got executed");

    }

    public void onTestFailure(ITestResult result) {
        test=extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,result.getName()+" Test Failed ");
        test.log(Status.INFO,result.getThrowable().getMessage());
        try {
            String imgPath= new baseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        }catch (Exception e){

            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        test=extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,result.getName()+" Test Failed ");
        test.log(Status.INFO,result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir")+"/reports/"+repName;
        File extentReports = new File(pathOfExtentReport);
        try {
            Desktop.getDesktop().browse(extentReports.toURI());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        /*
        try {
            URL url= new URL("file://"+System.getProperty("user.dir")+"/reports"+repName);
            //Create the email message
            ImageHtmlEmail Email=new ImageHtmlEmail();
            Email.setDataSourceResolver(new DataSourceUrlResolver(url));
            Email.setHostName("stamp.google.com");
            Email.setSmtpPort(465);
            Email.setAuthenticator(new DefaultAuthenticator("asysam8@gmail.com","Password"));
            Email.setSSLOnConnect(true);
            Email.setFrom("asysam8@gmail.com");
            Email.setSubject("Test Result");
            Email.setMsg("please find attache report");
            Email.addTo("zakzurdasht@gmail.com");
            Email.attach(url,"extent report","Please check the report");
            Email.send();

        }catch (Exception e)
        {
            e.printStackTrace();
        }

         */
    }



}
