package org.example.all;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium4NewLocators {

    @Test (groups = {"SmokeTest"})
    public void newLocators() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(3000);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(RelativeLocator.with(By.name("password")).below(username));

        username.sendKeys("Admin");
        password.sendKeys("admin123");

        WebElement btn = driver.findElement(RelativeLocator.with(By.xpath("//button")).below(username));
        btn.click();
       // WebElement
    }
}
