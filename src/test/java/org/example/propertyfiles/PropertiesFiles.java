package org.example.propertyfiles;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PropertiesFiles {
    @Test
    public void test3() throws IOException, InterruptedException {
		ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
	        FileReader reader=new FileReader("C:\\Users\\2445477\\IdeaProjects\\DemoQA_Project\\src\\test\\resources\\config2.properties");
	        Properties props=new Properties();
	        props.load(reader);
	        
	        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Documents\\Selenium\\chromedriver.exe");
	        driver.get(props.getProperty("url"));
	        Thread.sleep(2000);
	        driver.quit(); 
	 }
}
