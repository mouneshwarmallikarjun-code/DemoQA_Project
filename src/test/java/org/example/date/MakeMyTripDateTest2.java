package org.example.date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;


public class MakeMyTripDateTest2 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void openBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.makemytrip.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test
    public void tripDetails() throws InterruptedException, AWTException {
        List<WebElement> closeModal = driver.findElements(By.cssSelector("span.commonModal__close"));
        if (!closeModal.isEmpty()) {
            closeModal.get(0).click();
        }

        Thread.sleep(2000);
        WebElement dept = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("p[data-cy='departureDate']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", dept);
        //dept.click();
        Thread.sleep(2000);

        int expectedPlusday = 10;
        int dayAfterAdding = selectFutureDate(expectedPlusday);
        String expectedDate = String.valueOf(dayAfterAdding);

        System.out.println("dayAfterAdding:" + dayAfterAdding);
        Thread.sleep(3000);

        String selectedDate = driver.findElement(By.xpath("//p[@data-cy='departureDate']/span[1]")).getText();
//        System.out.println("selectedDate from UI:" + selectedDate);
//        System.out.println("Selected date calculate:" + expectedDate);
//        Assert.assertEquals(selectedDate, expectedDate, "Validation failed selected date is not correct");
    }

    public int selectFutureDate(int plusDays) throws InterruptedException {
        Thread.sleep(3000);

        WebElement todayDay = driver.findElement(By.xpath("\n" +
                "((//div[@class='DayPicker-Day DayPicker-Day--selected DayPicker-Day--today']//p) \n" +
                " | \n" +
                " (//div[@class='DayPicker-Day DayPicker-Day--selected']//p))[1]\n"));
        System.out.println("Today's day=" + todayDay);

        int todayDayInt = Integer.parseInt(todayDay.getText());
        System.out.println("todayDayInt value=" + todayDayInt);

        int futureDay = todayDayInt + plusDays; //9+5=14
        System.out.println("Future day is=" + futureDay);

        String futureDayString = String.valueOf(futureDay);
        System.out.println("futureDayString value" + futureDayString);

        Thread.sleep(1000);
        WebElement futureDateElement = driver.findElement(By.xpath("(//div[@class='DayPicker-Day DayPicker-Day--selected']//following::p/..//p[not(@class)])[" + futureDay + "]"));
        //futureDateElement.click();
        System.out.println("Xpath of future date::"+futureDateElement);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", futureDateElement);

        return futureDay;
    }


    //@AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

}
