package org.example.robot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;


public class FileUploadUsingRobot {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            // Navigate to the specified URL
            driver.get("https://demoqa.com/upload-download");
            Thread.sleep(2000);
            // Find the "Choose File" button element on the webpage
            WebElement chooseFile = driver.findElement(By.xpath("//input[@id='uploadFile']"));

            //chooseFile.sendKeys(Keys.TAB);
            // Use Actions class to move to the "Choose File" button and click on it
            Actions ac = new Actions(driver);
            ac.click(chooseFile).perform();

            // Initialize the Robot class
            Robot rb = new Robot();
            // Wait for 2 seconds to ensure the file dialog is open
            rb.delay(2000);

            // Copy the file path to the clipboard
            StringSelection filePath = new StringSelection(
                    System.getProperty("user.dir") + "\\CreateWorkbook.xlsx");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
            rb.delay(1000);

            // Simulate pressing CTRL +  V to paste the copied file path
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(2000);
            // Simulate pressing ENTER to confirm the file selections
            rb.keyPress(KeyEvent.VK_ENTER);
            //rb.keyRelease(KeyEvent.VK_ENTER);
//            Thread.sleep(2000);

            List<WebElement> uploadedPath = driver.findElements(By.cssSelector("p#uploadedFilePath"));
            if (uploadedPath.size() > 0) {
                System.out.println("File Uploaded successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //driver.quit();
        }
    }
}
