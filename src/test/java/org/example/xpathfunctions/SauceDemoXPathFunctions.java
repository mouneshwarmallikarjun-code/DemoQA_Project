package org.example.xpathfunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceDemoXPathFunctions {
    public static void main(String[] args) {
       // WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Use explicit waits for dynamic elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        try {
            // 0) Navigate to real site
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            // ===== starts-with() =====
            // Username field id is 'user-name' -> starts with 'user'
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[starts-with(@id,'user')]")
            ));
            username.sendKeys("standard_user");

            // ===== contains() on attribute =====
            // Password field id is 'password' -> contains 'pass'
            WebElement password = driver.findElement(
                    By.xpath("//input[contains(@id,'pass')]")
            );
            password.sendKeys("secret_sauce");

            // Login (plain id)
            driver.findElement(By.id("login-button")).click();

            // Inventory list visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'inventory_list')]")
            ));

            // ===== text() (exact) + contains() combined in a robust locator =====
            // Pick a specific product by its exact visible name using text()
            // Then click its "Add to cart" button inside the same product card
            WebElement backpackAddBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//div[contains(@class,'inventory_item')]"
                                    + "[.//div[contains(@class,'inventory_item_name') and normalize-space(text())='Sauce Labs Backpack']]"
                                    + "//button[normalize-space(text())='Add to cart']"
                    )
            ));
            backpackAddBtn.click();
            System.out.println("Added (text() exact): Sauce Labs Backpack");

            // ===== starts-with() on data-test + contains() on value =====
            // Add Bolt T-Shirt using data-test='add-to-cart-sauce-labs-bolt-t-shirt'
            WebElement boltAddBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[starts-with(@data-test,'add-to-cart-') and contains(@data-test,'bolt')]")
            ));
            boltAddBtn.click();
            System.out.println("Added (starts-with + contains on @data-test): Sauce Labs Bolt T-Shirt");

            // ===== last() on a node set =====
            // Add to cart the LAST product displayed on the page
            WebElement lastItemAddBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//div[contains(@class,'inventory_item')])[last()]//button[normalize-space(text())='Add to cart']")
            ));
            // For debug: read the product title of the last card
            WebElement lastItemTitle = driver.findElement(
                    By.xpath("((//div[contains(@class,'inventory_item')])[last()])//div[contains(@class,'inventory_item_name')]")
            );
            String lastName = lastItemTitle.getText().trim();
            lastItemAddBtn.click();
            System.out.println("Added (last() product): " + lastName);

            // Open cart (contains on class)
            WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@class,'shopping_cart_link')]")
            ));
            cartIcon.click();

            // Wait for cart items
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cart_list')]")
            ));

            // Use last() again to read the last cart item’s name (text())
            WebElement lastCartItemName = driver.findElement(
                    By.xpath("(//div[contains(@class,'cart_item')])[last()]//div[contains(@class,'inventory_item_name')]")
            );
            System.out.println("Last item in cart (from last()): " + lastCartItemName.getText().trim());

            // Demonstrate text() exact on a button in cart page – if there’s a "Continue Shopping"
            WebElement continueShoppingBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space(text())='Continue Shopping']")
            ));
            continueShoppingBtn.click();
            System.out.println("Clicked button found by exact text() = 'Continue Shopping'.");

            // Back on inventory, demonstrate contains(text()) for a partial match:
            // Pick any product title that contains the word 'Bike' and click it
            WebElement bikeLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'inventory_item_name') and contains(normalize-space(text()), 'Bike')]")
            ));
            System.out.println("Found product by contains(text()): " + bikeLink.getText().trim());

        } catch (TimeoutException te) {
            System.err.println("Timeout: " + te.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

