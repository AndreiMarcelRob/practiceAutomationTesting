package Utilities;

import Base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Util extends TestBase {


    public static boolean IsPresent(By by){
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public static void Refresh(){
        if(driver!=null){
            driver.navigate().refresh();
        }
    }


        public static String path;
        public static void TakeScreenShot(String i) throws IOException {
        Date d =new Date();
        String fileName = d.toString().replace(":","_").replace(" ","_");
        path = System.getProperty("user.dir")+"\\src\\test\\resources\\Prints"+i+"\\"+fileName+".jpg";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Prints"+i+"\\"+fileName+".jpg"));
    }


}
