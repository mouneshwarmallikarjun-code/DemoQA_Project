package org.example.multiselectdropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MultiSelectDropdownExample {

    public static void main(String[] args) throws InterruptedException {

        // Set ChromeDriver path
        //System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");

        // Launch browser
        WebDriver driver = new ChromeDriver();

        // Open the website
        driver.get("https://testkru.com/Elements/Dropdowns");
        driver.manage().window().maximize();

        Thread.sleep(2000);

        // Locate multi-select dropdown
        WebElement multiSelectDropdown = driver.findElement(By.id("multiSelect"));

        // Create Select object
        Select select = new Select(multiSelectDropdown);

        // Verify if it's multi-select
        if (select.isMultiple()) {
            System.out.println("This is a multi-select dropdown");

            // Select multiple options
//            select.selectByVisibleText("Selenium");
//            select.selectByVisibleText("Playwright");
//            select.selectByVisibleText("Cypress");

            select.selectByIndex(0);
            select.selectByIndex(1);
            select.selectByIndex(2);

            Thread.sleep(2000);

            // Get all selected options
            List<WebElement> selectedOptions = select.getAllSelectedOptions();

            System.out.println("Selected values:");
            for (WebElement option : selectedOptions) {
                System.out.println(option.getText());
            }

            Thread.sleep(2000);

            // Deselect one option
            select.deselectByVisibleText("Cypress");

            // Deselect all options
            Thread.sleep(2000);
            select.deselectAll();
        }

        // Close browser
        //driver.quit();
    }
}
