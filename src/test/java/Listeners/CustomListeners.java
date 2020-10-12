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
            TakeScreenShot("Fail");
            log.debug(iTestResult.getThrowable());
            Reporter.log(String.valueOf(iTestResult.getThrowable()));
            log.debug(iTestResult.getStatus());
            Reporter.log(String.valueOf(iTestResult.getStatus()));
            System.setProperty("org.uncommons.reportng.escape-output","false");
            Reporter.log("<a href=\""+path+"\">Screenshot</a>");
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
