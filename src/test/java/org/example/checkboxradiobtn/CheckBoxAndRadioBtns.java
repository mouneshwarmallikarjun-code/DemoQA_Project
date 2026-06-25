package org.example.checkboxradiobtn;

import java.util.List;

import io.qameta.allure.*;
import org.commoncode.CommonCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Epic("Check box Regression Testing using TestNG")
@Feature("DemoQA CheckBox and Radio button field")
public class CheckBoxAndRadioBtns extends CommonCode {



    @Story("Check box and Radio buttons selection with Valid User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Verify the details of user of id")
    @Test
    public void test2() throws InterruptedException {

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

		WebElement widGetsLink = driver.findElement(By.xpath("//h5[text()='Widgets']"));
		scrollToElement(widGetsLink, driver);
		
		clickByJs(widGetsLink, driver);
		takeScreenShot(driver, "WidGets page");
		
		WebElement eleLnk = driver.findElement(By.xpath("//div[text()='Elements']"));
		eleLnk.click();
		takeScreenShot(driver, "Clicked on Elements link");
		Thread.sleep(1000);
		
		WebElement checkBoxLnk = driver.findElement(By.xpath("//span[text()='Check Box']"));
		checkBoxLnk.click();
		takeScreenShot(driver, "Clicked on Check box link");
		
		WebElement checkBoxBtn = driver.findElement(By.xpath("//button[@title='Toggle']"));
		checkBoxBtn.click();
		Thread.sleep(1000);
		
		List<WebElement> allCheckBoxes = driver.findElements(By.xpath("//li[@class='rct-node rct-node-parent rct-node-collapsed']//span[@class='rct-checkbox']"));
 
		for(WebElement chk: allCheckBoxes) {
			chk.click();
			if(chk.isSelected()) {
				System.out.println("Check box is selected");
			}
			else
			{
				System.out.println("Check box is not selected");
			}
		}
		takeScreenShot(driver, "All Check box selected");
		
	}

}
