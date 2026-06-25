package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CheckBox {

    @Test
    public void test() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        WebElement elementLnk=driver.findElement(By.xpath("//h5[text()='Elements']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",elementLnk);
        elementLnk.click();
        Thread.sleep(2000);

        WebElement checkBoxLnk = driver.findElement(By.xpath("//span[text()='Check Box']"));
        checkBoxLnk.click();

        WebElement chekBoxArrow = driver.findElement(By.xpath("//span[text()='Home']"));
        chekBoxArrow.click();

//        WebElement desktopChk = driver.findElement(By.xpath("//span[text()='Desktop']//..//..//..//span[text()='Notes']"));
//        desktopChk.click();
    }
}
