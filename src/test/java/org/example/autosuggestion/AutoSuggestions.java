package org.example.autosuggestion;

import java.util.List;

import io.qameta.allure.*;
import org.commoncode.CommonCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

@Epic("Auto suggestion Regression Testing using TestNG")
@Feature("Google Home page module")
public class AutoSuggestions extends CommonCode {

    @Story("Auto suggestion Test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify Auto suggestion process")
    @Test
	public void test1() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		implicitWait(driver);
		driver.get("https://www.google.com/");
        Thread.sleep(2000);
        WebElement link=driver.findElement(By.partialLinkText("Enjoy Republic Day deals"));
        link.click();

		driver.findElement(By.name("q")).sendKeys("selenium");
		Thread.sleep(2000);

		String xp = "//span[contains(text(),'selenium')]";
		List<WebElement> allSuggestions = driver.findElements(By.xpath("//span[contains(text(),'selenium')]"));
		// To count number of suggetions
		int count = allSuggestions.size();
		System.out.println(count);
		// To print all the suggestions
		for (int i = 0; i < count; i++) {
		WebElement suggestion = allSuggestions.get(i);
		String text = suggestion.getText();
		System.out.println(text);
		}
//		// To click on last suggestion
//		allSuggestions.get(count - 1).click();
//		Thread.sleep(2000);
//        driver.navigate().back();
//        Thread.sleep(2000);
//        driver.navigate().refresh();


	}

}
