package org.example.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SelectClass {

    @Test
    public void select(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement dropdn=driver.findElement(By.id("dropdown-class-example"));

        Select dropdown = new Select(dropdn);
        dropdown.selectByValue("option3");
    }
}
