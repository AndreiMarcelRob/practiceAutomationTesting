package Listeners;

import Base.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;
import static Utilities.Util.TakeScreenShot;

import static Utilities.Util.path;


public class CustomListeners extends TestBase implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {
        try {
            TakeScreenShot("Fail");// ScreenShot that will put the screen in a file named Fail
            log.debug(iTestResult.getThrowable());//add a log with the fail reason
            Reporter.log(String.valueOf(iTestResult.getThrowable()));//add a report with the fail reason
            log.debug(iTestResult.getStatus());// add log Fail
            Reporter.log(String.valueOf(iTestResult.getStatus()));//add report Fail
            System.setProperty("org.uncommons.reportng.escape-output","false");// if i don't use this the HTML from report will be printed like a normal string
            Reporter.log("<a href=\""+path+"\">Screenshot</a>");//path to the screenshot but it doesn't because web browsers don't allow local resources, i will find another way
            System.out.println(path);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
