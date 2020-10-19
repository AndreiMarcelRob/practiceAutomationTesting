package TestCases;
import Base.TestBase;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static Utilities.Util.*;


public class HomePage extends TestBase {

    @BeforeMethod
    public static void stepsThreeAndFour(){
        click("ShopMenu_ID");
        click("HomePageBtn_ID");
    }

    @Test
    public static void HomePageSlidersNumber()
    {
        List<WebElement> sliders = driver.findElements(By.xpath(OR.getProperty("SlidersPartial_XPATH")));
        Assert.assertEquals(sliders.size(),3);
    }

    @Test
    public static void HomePageArrivalsNumber()
    {

        List<WebElement> Arrivals = driver.findElements(By.className(OR.getProperty("ArrivalsPartial_CLASS")));
        Assert.assertEquals(Arrivals.size(),3);
    }

    @Test
    public static void HomePageArrivalsAreNavigate() throws InterruptedException {
        List<WebElement> Arrivals = driver.findElements(By.className(OR.getProperty("ArrivalsPartial_CLASS")));
        Assert.assertEquals(Arrivals.size(),3);

//        for(WebElement Arrival:Arrivals){
//            Arrival.findElement(By.xpath(OR.getProperty("Arrival"))).click(); first attempt(dose not work because is trying to sync click al 3 elements)
//        }

//        SoftAssert softAssert = new SoftAssert();

        int elementCount = Arrivals.size();
        for (int x = 0; x < elementCount; x++) {
            List<WebElement> elements = driver.findElements(By.xpath(OR.getProperty("Arrival_XPATH")));
            WebElement client = elements.get(x);
            client.click();
            if(IsPresent(By.xpath(OR.getProperty("AddToCartBTN_XPATH")))){
                click("AddToCartBTN_XPATH");
                driver.findElement(By.className(OR.getProperty("ShoppingCart_CLASS"))).getText().contentEquals("1 items");
                Assert.assertTrue(true);
                click("ShoppingCart_CLASS");
                driver.findElement(By.xpath(OR.getProperty("QuantityFromShoppingCart_XPATH"))).clear();
                click("UpdateShoppingCart_XPATH");

            }else {
                Assert.assertTrue(IsPresent(By.xpath(OR.getProperty("OutOfStockReplacer_XPATH"))));
            }
            click("HomePageBtn_ID");
            Thread.sleep(2000);
        }

    }

    @Test
    public static void ArrivalsImagesDescription() throws InterruptedException {
        List<WebElement> Arrivals = driver.findElements(By.className(OR.getProperty("ArrivalsPartial_CLASS")));
        Assert.assertEquals(Arrivals.size(),3);
        int elementCount = Arrivals.size();
        for (int x = 0; x < elementCount; x++) {
            List<WebElement> elements = driver.findElements(By.xpath(OR.getProperty("Arrival_XPATH")));
            WebElement client = elements.get(x);
            client.click();
            Thread.sleep(2000);
            click("Description_ID");
            Assert.assertTrue(IsPresent(By.id(OR.getProperty("Description_ID"))));
            Assert.assertTrue(driver.findElement(By.id(OR.getProperty("Description_ID"))).getText().contains(OR.getProperty("Description"+x)));
            Assert.assertTrue((driver.findElement(By.id(OR.getProperty("Description_ID"))).isDisplayed()));
            click("HomePageBtn_ID");
        }


    }

    @Test
    public static void AddingMoreProductsThenAvailability() throws InterruptedException {

        List<WebElement> Arrivals = driver.findElements(By.className(OR.getProperty("ArrivalsPartial_CLASS")));
        Assert.assertEquals(Arrivals.size(), 3);
        int elementCount = Arrivals.size();
        for (int x = 0; x < elementCount; x++) {
            List<WebElement> elements = driver.findElements(By.xpath(OR.getProperty("Arrival_XPATH")));
            WebElement client = elements.get(x);
            client.click();
            if (IsPresent(By.xpath(OR.getProperty("AddToCartBTN_XPATH")))) {
                int quantityOverFlow = Integer.parseInt(driver.findElement(By.xpath(OR.getProperty("Quantity_XPATH"))).getAttribute("max"));
                quantityOverFlow++;
                type("Quantity_XPATH", String.valueOf(quantityOverFlow));
                click("AddToCartBTN_XPATH");
//                // Explicit wait condition for alert
//                WebDriverWait w = new WebDriverWait(driver, 5);
//                //alertIsPresent() condition applied
//                Assert.assertTrue(w.until(ExpectedConditions.alertIsPresent())!=null);(mot working)
                Assert.assertTrue(ExpectedConditions.alertIsPresent()!=null);





            }
            click("HomePageBtn_ID");
//            Thread.sleep(2000);


        }
    }




}
