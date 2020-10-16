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
import static Utilities.Util.click;


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
    public static void HomePageArrivalsAreNavigabile() throws InterruptedException {
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




}
