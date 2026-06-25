package org.example.all;

import java.io.IOException;
import java.util.List;

import org.commoncode.CommonCode;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AllValidationAtOne extends CommonCode {

    @Test
    public void test4() throws InterruptedException, IOException {
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
		

		alertPopUp(driver, "Welcome to Cognizant");
		Thread.sleep(2000);
		//takeScreenShot(driver, "Alert pop up");
		

		alertAccept(driver);
		Thread.sleep(1000);
		//takeScreenShot(driver, "Alert Ok button clicked");

//		Object result = js.executeScript(
//				"console.log('Welcome to Cognizant'); return 'done';");
//		System.out.println(result); // prints: done

//		Object result1 = js.executeScript(
//				"return console.log('Welcome to Cognizant');");
//		System.out.println(result1); // prints: done

		WebElement selectManuLink = driver.findElement(By.xpath("//span[text()='Select Menu']"));
		clickByJs(selectManuLink, driver);
		System.out.println("Select menu is clicked");
		takeScreenShot(driver, "Select menu is clicked");
		Thread.sleep(3000);

		WebElement selectValueDropDown = driver.findElement(By.id("oldSelectMenu"));
		scrollToElement(selectValueDropDown, driver);

		selectClassByIndex(driver, 4, selectValueDropDown);
		takeScreenShot(driver, "Drop down selected");

		WebElement datePickerLnk = driver.findElement(By.xpath("//span[text()='Date Picker']"));
		actionsMoveAndClickOnElement(driver, datePickerLnk);
		takeScreenShot(driver, "Actions Move to element");

		// Drag and drop
		WebElement InteractionsEle = driver.findElement(By.xpath("//div[text()='Interactions']"));
		scrollToElement(InteractionsEle, driver);
		InteractionsEle.click();
		takeScreenShot(driver, "Interactions element is clicked");
		Thread.sleep(2000);

		WebElement dropEle = driver.findElement(By.xpath("//span[text()='Droppable']"));
		dropEle.click();
		Thread.sleep(2000);

		WebElement dragMeEle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement dropEle2 = driver.findElement(By.xpath("(//p[text()='Drop here'])[1]"));
		actionsDragAndDrop(driver, dragMeEle, dropEle2);
		takeScreenShot(driver, "Actions Drag and drop");
		Thread.sleep(2000);


		// Elements and text box fill
		WebElement eleLnk = driver.findElement(By.xpath("//div[text()='Elements']"));
		eleLnk.click();
		takeScreenShot(driver, "Elements link clicked");
		Thread.sleep(2000);

		WebElement textBoxLnk = driver.findElement(By.xpath("//span[text()='Text Box']"));
		textBoxLnk.click();
		Thread.sleep(2000);

		WebElement fullNameTxb = driver.findElement(By.id("userName"));
		WebElement emailTxb = driver.findElement(By.id("userEmail"));
		WebElement currentAddTxb = driver.findElement(By.id("currentAddress"));
		WebElement perAddTxb = driver.findElement(By.id("permanentAddress"));
		WebElement submitBtn = driver.findElement(By.id("submit"));

		String fullName = "Shiva";
		enterTextByJs(driver, fullNameTxb, fullName);
		Thread.sleep(1000);
		
		//WebElement fn = driver.findElement(By.xpath("//input[@placeholder='Full Name']"));
		//String placeHolder=fn.getAttribute("placeholder");
		//String getFullName=getText(driver, fullNameTxb);
		//System.out.println("Entered fullname is:"+placeHolder);
		
		String emailId="Shiva@gmail.com";
		enterTextByJs(driver, emailTxb, emailId);
		//String getEmail=getTextByJs(driver, emailTxb);
		//System.out.println("Entered email is:"+getEmail);
		Thread.sleep(1000);
		
		String curentAdd = "Chennai";
		enterTextByJs(driver, currentAddTxb, curentAdd);
		Thread.sleep(1000);
		
		String perAdd= "Bangalore";
		enterTextByJs(driver, perAddTxb,perAdd);
		Thread.sleep(1000);
		clickByJs(submitBtn, driver);
		takeScreenShot(driver, "Form filled and submitted");
		Thread.sleep(1000);
		
		WebElement savedName= driver.findElement(By.cssSelector("p#name"));
		String savedNames=getTextByJs(driver, savedName);
		System.out.println("Saved names::"+savedNames);
		
		String splitName = savedNames.split(":")[1];
		System.out.println("Splitted name:"+splitName);
		
		scrollToElement(submitBtn, driver);
		Thread.sleep(1000);
		scrollToElement(eleLnk, driver);
		Thread.sleep(2000);
		scrollToBottom(driver);
		takeScreenShot(driver, "Form saved");
		Thread.sleep(1000);
		scrollToTop(driver);
		
		
		
		//Frames
		WebElement alertsFramesLink = driver.findElement(By.xpath("//div[text()='Alerts, Frame & Windows']"));
		clickByJs(alertsFramesLink, driver);
		takeScreenShot(driver, "Alerts/Frame Link is clicked");
		Thread.sleep(1000);
		WebElement alertsLnk = driver.findElement(By.xpath("//span[text()='Frames']"));
		clickByJs(alertsLnk, driver);
		Thread.sleep(1000);
		takeScreenShot(driver, "Alerts link clicked");
		
		
		//switchFrameByIndex(driver,0);
		//System.out.println("Switched frame");
		
		//System.out.println(driver.getTitle());

//--------------------FRAME------------------------------------>		
		//Frame1
		WebElement frame1 = driver.findElement(By.cssSelector("iframe#frame1"));
		switchFrameByWebElement(driver,frame1);
		takeScreenShot(driver, "Switch to Frame");
		WebElement eleSamplePage = driver.findElement(By.xpath("(//*[@id='sampleHeading'])[1]"));
		String frame1Text=getText(driver, eleSamplePage);
		System.out.println("Frame1 String::"+frame1Text);
		takeScreenShot(driver, "Frame 1 Text");
		
		switchToDefault(driver);
		Thread.sleep(1000);
		scrollToBottom(driver);
		takeScreenShot(driver, "Switch default frame");
		
		Thread.sleep(1000);
		//Frame2
//		WebElement frame2 = driver.findElement(By.cssSelector("iframe#frame2"));
//		switchFrameByWebElement(driver,frame2);
		//ScrollToElement(frame2, driver);
		//driver.switchTo().frame(2);
//		WebElement eleSamplePage2 = driver.findElement(By.xpath("(//*[@id='sampleHeading'])[1]"));
//		String frame1Text2=getText(driver, eleSamplePage2);
//		System.out.println("Frame2 String::"+frame1Text2);
//<--------------------FRAME COMPLETED--------------------------------->				
		
		
//<---------------------NESTED FRAME----------------------------------->	
		
		
		//switchToDefault(driver);
		Thread.sleep(1000);
		WebElement nestedFrameLnk = driver.findElement(By.xpath("//span[text()='Nested Frames']"));
		nestedFrameLnk.click();
		takeScreenShot(driver, "Clicked on Nested frame link");
		Thread.sleep(1000);
		WebElement outerFrame = driver.findElement(By.xpath("//iframe[@id='frame1']"));
		switchFrameByWebElement(driver, outerFrame);
		Thread.sleep(1000);
		WebElement textOuterFrame = driver.findElement(By.xpath("//body[text()='Parent frame']"));
		String textOfOuterFrame= getTextByJs(driver, textOuterFrame);
		System.out.println("Text contained in outer frame:"+textOfOuterFrame);
		takeScreenShot(driver, "Get frame text");
		
		WebElement innerFrame = driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"));
        fluentWait2(driver,innerFrame);
		switchFrameByWebElement(driver, innerFrame);
		WebElement textInnerFrame = driver.findElement(By.xpath("//p[text()='Child Iframe']"));
		String textOfInnerrFrame= getTextByJs(driver, textInnerFrame);
		System.out.println("Text contained in outer frame:"+textOfInnerrFrame);
		Thread.sleep(1000);


		
		
	
//<-----------------------NESTED FRAME COMPLETED--------------------------------->

//<-----------------------WINDOW HANDLING---------------------------------------->
		
		switchToDefault(driver);
		Thread.sleep(1000);
		WebElement browserWindowLnk = driver.findElement(By.xpath("//span[text()='Browser Windows']"));
		scrollToElement(browserWindowLnk, driver);
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

		WebElement newTabText = driver.findElement(By.id("sampleHeading"));
		String newTabTextMsg = getText(driver, newTabText);
		System.out.println("New Tab(window) text message:"+newTabTextMsg);
		takeScreenShot(driver, "Child window text");
		//driver.quit();
//<------------------WINDOW HANDLING COMPLETED------------------------------>


//<-----------------FIND ELEMENTS------------------------------------------->
		Thread.sleep(2000);
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		takeScreenShot(driver, "Launched new URL");
		List<WebElement> allProducts =driver.findElements(By.className("product-name"));
		for(WebElement product: allProducts) {
			System.out.println(getText(driver, product));
		}
		System.out.println("Total products::"+allProducts.size());
		takeScreenShot(driver, "All products");



//<------------------------------------------------------------------------->


//<--------------------Check box and Radio buttons-------------------------->



//<-------------------------------------------------------------------------->



		//takeScreenShot(driver, "Frames");
		System.out.println("Execution has been completed successfully...!");

		//driver.quit();

	}

}
