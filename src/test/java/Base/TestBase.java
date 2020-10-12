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

    @BeforeSuite
    public void setUP() throws IOException {
        Date date =new Date();
        System.setProperty("current.date",date.toString().replace(":","_").replace(" ","_"));
        PropertyConfigurator.configure("./src/test/resources/Properties/log4j.properties");
        if (driver==null) {
            fisier = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\Config.properties");
            config.load(fisier);
            log.debug("file loded");
            fisier = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\OR.properties");
            OR.load(fisier);
            log.debug("file loded");
        }
        if(config.getProperty("browser").equals("chrome")){
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

        driver.get(config.getProperty("testSiteURL"));
        log.debug("open url");
        driver.manage().window().maximize();
        log.debug("window maximize");
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlyWaitTime")), TimeUnit.SECONDS);
//      driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);



    }

    @AfterMethod
    public static void CleanFealds() {
        Refresh();
    }


    @AfterSuite
    public void tearDown(){
        if(driver!=null){
            driver.close();
        }
    }




}
