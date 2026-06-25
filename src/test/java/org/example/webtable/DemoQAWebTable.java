package org.example.webtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.commoncode.CommonCode.*;

public class DemoQAWebTable {

    @Test
    public void testWebTable() throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        implicitWait(driver);

        driver.get("https://demoqa.com/");
        takeScreenShot(driver, "DemoQA Home page");
        String title = driver.getTitle();
        System.out.println("Title of the page:" + title);

        String url = driver.getCurrentUrl();
        System.out.println("URL of the page:" + url);
        Thread.sleep(2000);

        scrollToBottom(driver);
        Thread.sleep(2000);
        // Elements and text box fill
        WebElement eleLnk = driver.findElement(By.xpath("//h5[text()='Elements']"));
        //expWait("//h5[text()='Elements']",driver);
        //scrollToElement(eleLnk,driver);
        clickByJs(eleLnk,driver);
        takeScreenShot(driver, "Elements link clicked");
        Thread.sleep(2000);

        WebElement webTableLnk = driver.findElement(By.xpath("//span[text()='Web Tables']"));
        //scrollToElement(webTableLnk,driver);
        scrollToTop(driver);
        clickByJs(webTableLnk,driver);

        List<WebElement> tableData = driver.findElements(By.xpath("//table//tbody//tr//td"));
        for(int i=1;i<tableData.size()-1;i++){
            System.out.println(tableData.get(i).getText());
        }
    }
    }
