package org.commoncode;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class CommonCode {
	private static JavascriptExecutor js = null;
	
	public static void implicitWait(WebDriver driver) {
											//Before (Time,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public static void expWait(String ele,WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ele)));
	}
	
	public static void scrollToElement(WebElement ele, WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}
	
	public static void clickByJs(WebElement ele,WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}
	
	public static void alertAccept(WebDriver driver) {
		Alert a = driver.switchTo().alert();
		a.accept();
	}
	
	public static void alertDismiss(WebDriver driver) {
		Alert a = driver.switchTo().alert();
		a.dismiss();
	}

	public static void alertSendKeys(WebDriver driver,String sendMessage) {
		Alert a = driver.switchTo().alert();
		a.sendKeys(sendMessage);
	}
	
	public static String alertGetText(WebDriver driver) {
		Alert a = driver.switchTo().alert();
		String alerText=a.getText();
		return alerText;
	}
	
	public static void alertPopUp(WebDriver driver,String popMsg) {
		js = (JavascriptExecutor) driver;
		js.executeScript("alert('"+popMsg+"')");
	}
	
	public static void selectClassByIndex(WebDriver driver,int index,WebElement ele) {
		Select s = new Select(ele);
		s.selectByIndex(index);
	}
	
	public static void selectByVisibleText(WebDriver driver,String visibleTxt,WebElement ele) {
		Select s = new Select(ele);
		s.selectByVisibleText(visibleTxt);
	}
	
	public static void actionsMoveAndClickOnElement(WebDriver driver,WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).build().perform();
		act.click(ele).build().perform();
	}
	
	public static void actionsDragAndDrop(WebDriver driver,WebElement dragEle,WebElement dropEle) {
		Actions act = new Actions(driver);
		act.dragAndDrop(dragEle, dropEle).perform();
	}
	
	
	public static void enterTextByJs(WebDriver driver,WebElement ele,String text) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='"+text+"'", ele);
	}
	
	public static void scrollToBottom(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public static void scrollToTop(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollTop)");
	}
	
	public static String getText(WebDriver driver,WebElement ele) {
		String text=ele.getText();
		return text;
	}
	
	public static String getTextByAttribute(WebDriver driver,WebElement ele,String attributeName) {
		String text= ele.getAttribute(attributeName);
		return text;
	}
	
	public static String getTextByJs(WebDriver driver,WebElement ele) {
		js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].textContent;", ele);
	}
	
	public static void switchFrameByIndex(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	public static void switchFrameByWebElement(WebDriver driver,WebElement ele) {
		driver.switchTo().frame(ele);
	}
	
	public static void switchToDefault(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

//    public static WebElement fluentWait(WebDriver driver,WebElement locator){
//            Wait<WebDriver> wait = new FluentWait<>(driver)
//                    .withTimeout(Duration.ofSeconds(30))       // max wait time
//                    .pollingEvery(Duration.ofSeconds(2))       // check every 2 sec
//                    .ignoring(NoSuchElementException.class);
//        return wait.until(d -> d.findElement((By) locator));
//            }

    public static WebElement fluentWait2(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoAlertPresentException.class);

        return wait.until(d -> (element.isDisplayed() ? element : null));
    }

	
	public static void takeScreenShot(WebDriver driver, String name) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		try {
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File(System.getProperty("user.dir") + "/Screenshots/" + name + ".jpeg");
//			FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE),
//					new File(System.getProperty("user.dir") + "/CheckBoxRadioBtns/" + name + ".jpeg"));
            FileUtils.copyFile(src,dest);
			System.out.println(name + ": Screenshot has been captured.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//Handling shadow DOM-->Hidden elements
    public static WebElement getShadowRoot(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
    }

}
