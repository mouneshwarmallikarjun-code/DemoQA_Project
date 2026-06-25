package org.example.practice;

import org.commoncode.CommonCode;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Frames extends CommonCode {

    @Test
    public void frames() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/nestedframes");

        driver.findElement(By.xpath("//span[text()='Nested Frames']")).click();
        Thread.sleep(2000);
       WebElement frame2=driver.findElement(By.id("frame1"));

        driver.switchTo().frame(frame2);
        Thread.sleep(2000);

        String parentEle= driver.findElement(By.xpath("//body[text()='Parent frame']")).getText();
        System.out.println("Parent window name is:"+parentEle);

        WebElement innerFrame = driver.findElement(By.xpath("(//iframe)[2]"));
        driver.switchTo().frame(0);

        JavascriptExecutor js =(JavascriptExecutor)driver;
        String text= js.executeScript("return arguments[0].textContent;",innerFrame).toString();
        System.out.println("Child window name is:"+text);

        //body[text()='Parent frame']

//        String childFrameName = driver.findElement(By.xpath("//p[text()='Child Iframe']")).getText();
//        System.out.println("Child window name is:"+childFrameName);

    }
}
