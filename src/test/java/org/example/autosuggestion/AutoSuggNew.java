package org.example.autosuggestion;

import org.commoncode.CommonCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;

public class AutoSuggNew extends CommonCode {
    @Test
    public void test1() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        //implicitWait(driver);
        Thread.sleep(2000);
        driver.get("https://www.google.com/");
        //Thread.sleep(2000);


        WebElement ele=driver.findElement(By.name("q"));
        //Thread.sleep(2000);
        fluentWait2(driver,ele);
        System.out.println("Fluent wait is cleared");
        ele.sendKeys("selenium");

        //String xp = "//span[contains(text(),'selenium')]";
        List<WebElement> allSuggestions = driver.findElements(By.xpath("//span[contains(text(),'selenium')]"));
        // To count number of suggetions
        int count = allSuggestions.size();
        System.out.println(count);
        // To print all the suggestions
        for (int i = 0; i < count; i++) {
            WebElement suggestion = allSuggestions.get(i);
            String text = suggestion.getText();
            System.out.println(text);
        }
    }
}
