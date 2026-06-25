package org.example.testng;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGAssertionsExample {
    WebDriver driver;
//Setting up the browser
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyGoogleTitle() throws InterruptedException {
        driver.get("https://www.google.com");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Google1";

        //Assert.fail();

        // TestNG Assertions
       // Assert.assertEquals(actualTitle, expectedTitle, "Title does not match!");
//        Assert.assertTrue(actualTitle.contains("Google"), "Title should contain Google");
//        Assert.assertNotNull(actualTitle, "Title should not be null");
        //Assert.assertTrue(false);
        //Assert.fail();

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(actualTitle, expectedTitle, "Title does not match!");


        WebElement searchBox = driver.findElement(By.xpath("//textarea[@title='Search']"));
        searchBox.sendKeys("Java");
        Thread.sleep(4000);

        sa.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

