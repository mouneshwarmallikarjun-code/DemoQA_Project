package org.example.all;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeMyTrip {


    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;



    int lowestPrice = Integer.MAX_VALUE;
    WebElement cheapestSelectBtn;
    String cheapestCabName;

    // ---------- API PAGE CHECK ----------
    private boolean isApiResponsePage() {
        try {
            String bodyText = driver.findElement(By.tagName("body")).getText();
            return bodyText.contains("200-OK") || bodyText.startsWith("{");
        } catch (Exception e) {
            return false;
        }
    }
    private void selectCityFromAutosuggest(String cityName) {

        // Wait for autosuggest results to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'sr_city')]")
        ));

        // Click the city that matches text
        WebElement city = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@class,'sr_city') and contains(text(),'" + cityName + "')]")
        ));

        city.click();
    }


    // ---------- SETUP (RUNS ONCE) ----------
    @BeforeClass
    public void setup() {

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        ChromeOptions options = new ChromeOptions();
//
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.default_content_setting_values.notifications", 1);
//        prefs.put("profile.default_content_setting_values.geolocation", 1);
//        prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
//        prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
//
//        options.setExperimentalOption("prefs", prefs);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("disable-infobars");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " + "AppleWebKit/537.36 (KHTML, like Gecko) " + "Chrome/120.0.0.0 Safari/537.36");
        driver = new ChromeDriver(options);

        //     WebDriver driver = new ChromeDriver(options);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        js = (JavascriptExecutor) driver;

        driver.get("https://www.makemytrip.com/");
    }

    // ---------- TC01 ----------
    @Test(priority = 1)
    public void verifyHomePageLoads() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.id("fromCity")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Cabs']"))
        ));
        Assert.assertFalse(isApiResponsePage(), "API page loaded instead of UI");
    }

    // ---------- TC02 ----------
    @Test(priority = 2)
    public void closeLoginPopup() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='commonModal__close']")
            )).click();
        } catch (Exception ignored) {}
    }

    // ---------- TC03 ----------
    @Test(priority = 3)
    public void openCabsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Cabs']")
        )).click();
    }

    // ---------- TC04 ----------
    @Test(priority = 4)
    public void selectFromCity() {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("fromCity"))).click();

        WebElement fromInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='From']")
        ));
        fromInput.sendKeys("Delhi");

        selectCityFromAutosuggest("Delhi");
    }

    // ---------- TC05 ----------
    @Test(priority = 5)
    public void selectToCity() {

        WebElement toInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='To']")
        ));
        toInput.sendKeys("Manali");

        selectCityFromAutosuggest("Manali");

        js.executeScript("document.body.click();");
    }


    // ---------- TC06 ----------
    @Test(priority = 6)
    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Search']")
        )).click();
    }

    // ---------- TC07 ----------
    @Test(priority = 7)
    public void verifyListingPage() {
        wait.until(ExpectedConditions.urlContains("/cabs/listing"));
        Assert.assertFalse(isApiResponsePage(), "API page loaded after search");
    }

    // ---------- TC08 ----------
    @Test(priority = 8)
    public void applySUVFilter() {
        try {
            WebElement suvFilter = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'SUV')]")
            ));
            suvFilter.click();
        } catch (Exception ignored) {}
    }

    // ---------- TC09 ----------
    @Test(priority = 9)
    public void printAllSUVCabsAndPrices() {

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@data-testid,'CAB_CARD SUV')]")
        ));

        List<WebElement> suvCabs = driver.findElements(
                By.xpath("//div[contains(@data-testid,'CAB_CARD SUV')]")
        );

        for (WebElement card : suvCabs) {
            try {
                String cabName = card.findElement(
                        By.xpath(".//span[@data-testid='CAB_TITLE']")
                ).getText();

                String priceText = card.findElement(
                        By.xpath(".//span[contains(@class,'__price')]")
                ).getText();

                int price = Integer.parseInt(
                        priceText.replace("₹", "").replace(",", "").trim()
                );

                System.out.println(cabName + " | ₹" + price);

                if (price < lowestPrice) {
                    lowestPrice = price;
                    cheapestCabName = cabName;
                    cheapestSelectBtn = card.findElement(
                            By.xpath(".//div[contains(@class,'selectCabBtn')]")
                    );
                }

            } catch (Exception ignored) {}
        }
    }

    // ---------- TC10 ----------
    @Test(priority = 10)
    public void selectLowestPricedSUV() {
        Assert.assertNotNull(cheapestSelectBtn, "No SUV cab found");
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", cheapestSelectBtn);
        cheapestSelectBtn.click();
        System.out.println("✅ Cheapest SUV selected: " +
                cheapestCabName + " | ₹" + lowestPrice);
    }

    // ---------- TEARDOWN ----------
    @AfterClass
    public void tearDown() {
        // driver.quit(); // enable if needed
    }
}
