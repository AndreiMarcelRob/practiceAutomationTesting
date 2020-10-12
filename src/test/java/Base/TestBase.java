package Base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static Utilities.Util.Refresh;

public class TestBase {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static FileInputStream fisier;

    @BeforeSuite//before each suite this code will run, I am planning to put here something to switch between browsers, and then just add the suite 3 times in runner file
    public void setUP() throws IOException {
        Date date =new Date();//I need this to create unique prints files etc. names, plus its helpful to know the time the etc. are created
        System.setProperty("current.date",date.toString().replace(":","_").replace(" ","_"));//modify data to be accepted in file names
        PropertyConfigurator.configure("./src/test/resources/Properties/log4j.properties");//the path to the file that contains logs file creation data and properties
        if (driver==null) {// created 2 files where i will add xpath, id, css selector so i will have a cleaner code, i will get the value from here
            fisier = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\Config.properties");
            config.load(fisier);
            log.debug("file loded");
            fisier = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\OR.properties");
            OR.load(fisier);
            log.debug("file loded");
        }
        if(config.getProperty("browser").equals("chrome")){// switch between executables (drivers) i can get the value from a file or excel, don't know now
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\chromedriver.exe");
            driver=new ChromeDriver();
            log.debug("ChromeDriver set");
        }else if(config.getProperty("browser").equals("IE")){
            System.setProperty("WebDriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\IEDriverServer.exe");
            driver=new InternetExplorerDriver();
            log.debug("InternetExplorerDriver set");
        }else {
            System.setProperty("WebDriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\geckodriver.exe");
            driver=new FirefoxDriver();
            log.debug("FirefoxDriver set");
        }

        driver.get(config.getProperty("testSiteURL"));//go to test site
        log.debug("open url");
        driver.manage().window().maximize();//maximize the window
        log.debug("window maximize");
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlyWaitTime")), TimeUnit.SECONDS);
//      driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);



    }

    @AfterMethod//just a refresh that will implements after all methods, its good for a log in suite
    public static void CleanFealds() {
        Refresh();
    }


    @AfterSuite// close the window after suite
    public void tearDown(){
        if(driver!=null){
            driver.close();
        }
    }




}
