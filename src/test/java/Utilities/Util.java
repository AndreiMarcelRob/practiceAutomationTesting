package Utilities;

import Base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Util extends TestBase {


    public static boolean IsPresent(By by){// this will check if the element is on the page regardless of it being visible or not(for visible we have display method)
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public static Boolean IsNotPresent(By by){
        try {
            driver.findElement(by);
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public static void Refresh(){ //the refresh method we call in CleanFealds
        if(driver!=null){
            driver.navigate().refresh();
        }
    }


        public static String path;// I needed this so I can add the screenShot in HTML report
        public static void TakeScreenShot(String i) throws IOException {//a metod we will use onTestFail listener, I added i so i can call the method for a particular case and put the screenShots in separate files
        Date d =new Date();
        String fileName = d.toString().replace(":","_").replace(" ","_");
        path = System.getProperty("user.dir")+"\\src\\test\\resources\\Prints"+i+"\\"+fileName+".jpg";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Prints"+i+"\\"+fileName+".jpg"));
    }

        public static void click (String selector){
            if(selector.endsWith("_ID")){
                driver.findElement(By.id(OR.getProperty(selector))).click();
                Reporter.log("click on"+selector);
            } else if(selector.endsWith("_XPATH")){
                driver.findElement(By.xpath(OR.getProperty(selector))).click();
                Reporter.log("click on"+selector);
            }else if(selector.endsWith("_CLASS")){
                driver.findElement(By.className(OR.getProperty(selector))).click();
                Reporter.log("click on"+selector);
            }
        }

        public static void type(String selector,String value){
            if(selector.endsWith("_ID")){
                driver.findElement(By.id(OR.getProperty(selector))).sendKeys(value);
                Reporter.log("click on"+selector);
            } else if(selector.endsWith("_XPATH")){
                driver.findElement(By.xpath(OR.getProperty(selector))).sendKeys(value);
                Reporter.log("click on"+selector);
            }else if(selector.endsWith("_CLASS")){
                driver.findElement(By.className(OR.getProperty(selector))).sendKeys(value);
                Reporter.log("click on"+selector);
            }
        }
}
