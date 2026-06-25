package org.example.tooltip;

import org.commoncode.CommonCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ToolTipDemo extends CommonCode {

    @Test
    public void test5() throws InterruptedException {
		String baseUrl = "https://jqueryui.com/tooltip/"; 
		//System.setProperty("webdriver.chrome.driver","<path of browser driver file>");
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
		driver.get(baseUrl);
		Thread.sleep(1000);
		String expectedTooltip = "We ask for your age only for statistical purposes."; 

		// Find the age field
        WebElement frame1=driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame1);
        Thread.sleep(1000);

		WebElement ele = driver.findElement(By.id("age"));
        Actions ac  = new Actions(driver);
        ac.moveToElement(ele).perform();
        Thread.sleep(3000);

        WebElement text= driver.findElement(By.xpath("//p[text()='Hover the field to see the tooltip.']"));
		//scrollToElement(text, driver);
        Thread.sleep(1000);
		//get the value of the "title" attribute 
		String actualTooltip = ele.getAttribute("title");
		

		//Comparing tooltip’s value with expected value 
		System.out.println("Actual Title of Tool Tip::"+actualTooltip);
		if(actualTooltip.equals(expectedTooltip)) { 
		System.out.println("Test Case Passed"); 
		} else {
            System.out.println("Test Case Failed");
        }
		//driver.close();
	}

}
