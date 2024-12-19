package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUtility implements ITestListener {
    public ExtentSparkReporter sparkReporter;       // responsible for UI of the report
    public ExtentReports extentReports;         //project common information [project name, module name, username, OS name...]
    public ExtentTest extentTest;         //responsible for writing the tests or creating the entries of failed or passed or skipped testcases

    String reportName;

    public void onStart(ITestContext iTestContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
        reportName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);      // location of the report
        sparkReporter.config().setDocumentTitle("RA_Automation_Framework");     //title of the report
        sparkReporter.config().setReportName("Pet Store Users API");    // name of the API
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "Pet Store Users API");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("user", "Keerthi");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        extentTest = extentReports.createTest(iTestResult.getName());
        extentTest.assignCategory(iTestResult.getMethod().getGroups());
        extentTest.createNode(iTestResult.getName());
        extentTest.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        extentTest = extentReports.createTest(iTestResult.getName());
        extentTest.createNode(iTestResult.getName());
        extentTest.assignCategory(iTestResult.getMethod().getGroups());
        extentTest.log(Status.FAIL, "Test Failed");
        extentTest.log(Status.FAIL, iTestResult.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        extentTest = extentReports.createTest(iTestResult.getName());
        extentTest.createNode(iTestResult.getName());
        extentTest.assignCategory(iTestResult.getMethod().getGroups());
        extentTest.log(Status.SKIP, "Test Skipped");
        extentTest.log(Status.SKIP, iTestResult.getThrowable().getMessage());
    }

    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();      // if this method is not called the report will not be generated
    }
}
