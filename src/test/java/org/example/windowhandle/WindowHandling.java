package org.example.windowhandle;

import org.commoncode.CommonCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowHandling extends CommonCode {

    @Test
    public void windowTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        //switchToDefault(driver);
        driver.get("https://demoqa.com/browser-windows");
        Thread.sleep(1000);
        WebElement browserWindowLnk = driver.findElement(By.xpath("//span[text()='Browser Windows']"));
        //scrollToElement(browserWindowLnk, driver);
        takeScreenShot(driver, "Parent window");
        Thread.sleep(1000);
        browserWindowLnk.click();
        Thread.sleep(1000);
        String originalWindow = driver.getWindowHandle();
        //expWait(newTabBtn, driver);
        WebElement newTabBtn = driver.findElement(By.xpath("//button[text()='New Tab']"));
        expWait("//button[text()='New Tab']", driver);
        newTabBtn.click();
        takeScreenShot(driver, "New Tab");

        Thread.sleep(1000);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                takeScreenShot(driver, "Switched to child window");
                break;
            }
        }
        String childWindowName = driver.findElement(By.xpath("//h1")).getText();
        System.out.println("Child window name contains:"+childWindowName);
    }
}
