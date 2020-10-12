package TestCases;
import Base.TestBase;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import static Utilities.Util.IsPresent;


public class HomePage extends TestBase {

    @BeforeMethod
    public static void stepsThreeAndFour(){
        driver.findElement(By.id(OR.getProperty("ShopMenu"))).click();
        driver.findElement(By.id(OR.getProperty("HomePageBtn"))).click();
    }

    @Test
    public static void HomePageSlidersNumber()
    {
        List<WebElement> sliders = driver.findElements(By.xpath(OR.getProperty("SlidersPartial")));
        Assert.assertEquals(sliders.size(),3);
    }

    @Test
    public static void HomePageArrivalsNumber()
    {

        List<WebElement> Arrivals = driver.findElements(By.className(OR.getProperty("ArrivalsPartial")));
        Assert.assertEquals(Arrivals.size(),3);
    }

    @Test
    public static void HomePageArrivalsAreNavigabile() throws InterruptedException {
        List<WebElement> Arrivals = driver.findElements(By.className(OR.getProperty("ArrivalsPartial")));
        Assert.assertEquals(Arrivals.size(),3);

//        for(WebElement Arrival:Arrivals){
//            Arrival.findElement(By.xpath(OR.getProperty("Arrival"))).click(); first attempt(dose not work because is trying to sync click al 3 elements)
//        }

//        SoftAssert softAssert = new SoftAssert();

        int elementCount = Arrivals.size();
        for (int x = 0; x < elementCount; x++) {
            List<WebElement> elements = driver.findElements(By.xpath(OR.getProperty("Arrival")));
            WebElement client = elements.get(x);
            client.click();
            if(IsPresent(By.xpath(OR.getProperty("AddToCartBTN")))){
                driver.findElement(By.xpath(OR.getProperty("AddToCartBTN"))).click();
                driver.findElement(By.xpath(OR.getProperty("ShoppingCart"))).getText().contentEquals("1 items");
                Assert.assertTrue(true);
                driver.findElement(By.xpath(OR.getProperty("ShoppingCart"))).click();
                driver.findElement(By.xpath(OR.getProperty("QuantityFromShoppingCart"))).clear();
                driver.findElement(By.xpath(OR.getProperty("UpdateShoppingCart"))).click();

            }else {
                Assert.assertTrue(IsPresent(By.xpath(OR.getProperty("OutOfStockReplacer"))));
            }
            driver.findElement(By.id(OR.getProperty("HomePageBtn"))).click();
            Thread.sleep(2000);
        }

    }




}
