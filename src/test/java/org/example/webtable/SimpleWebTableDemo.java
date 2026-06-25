package org.example.webtable;


//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SimpleWebTableDemo {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to sample table page
        driver.get("https://the-internet.herokuapp.com/tables");

        // Locate the table
        WebElement table = driver.findElement(By.id("table1"));

        // Get all rows (including header)
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        System.out.println("Total rows: " + rows.size());

        // Print each row text
        for (WebElement row : rows) {
            System.out.println(row.getText());
        }

        // Get all header columns
        List<WebElement> headers = table.findElements(By.cssSelector("thead th"));
        System.out.println("\nHeaders:");
        for (WebElement header : headers) {
            System.out.print(header.getText() + " | ");
        }

        // Get all data rows
        List<WebElement> dataRows = table.findElements(By.cssSelector("tbody tr"));
        System.out.println("\n\nData Rows:");
        for (WebElement dataRow : dataRows) {
            List<WebElement> cells = dataRow.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + " | ");
            }
            System.out.println();
        }

        // Example: Get specific cell (Row 2, Column 3)
        WebElement specificCell = driver.findElement(By.xpath("//*[@id='table1']//tbody/tr[2]/td[3]"));
        System.out.println("\nSpecific Cell (Row 2, Col 3): " + specificCell.getText());

        // Close browser
        driver.quit();
    }
}

