package org.example.robot;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class RobotCtrlACV_W3Schools {
    public static void main(String[] args) throws Exception {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_textarea");

            // Switch into the result iframe that contains the <textarea>
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeResult"));

            // Locate and focus the textarea
            WebElement ta = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("textarea")));
            ta.click();
            ta.clear();
            ta.sendKeys("This is a Robot shortcut demo.\nCTRL+A, CTRL+C, then CTRL+V will duplicate the content.");

            // Small sleep to ensure OS focus on the textarea
            Thread.sleep(1000);

            // Perform CTRL+A, CTRL+C, CTRL+V via Robot
            Robot robot = new Robot();
            robot.setAutoDelay(2000);

            // CTRL + A
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(2000);

            // CTRL + C
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(2000);

            // CTRL + V (paste → duplicates the content)
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // Optional: paste again to confirm
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // Back to top-level if you need further actions
            driver.switchTo().defaultContent();

            Thread.sleep(1500); // just to see the effect before closing
        } finally {
           // driver.quit();
        }
    }
}

