package org.example.desiredcapabilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;

public class BrowserStackExample {

    @Test
        public void test1() throws Exception {
            ChromeOptions chromeOptions = new ChromeOptions();
        //EdgeOptions edgeOptions = new EdgeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--headless=new");


        //edgeOptions.addArguments("--start-maximized");
        //edgeOptions.addArguments("--incognito");

            MutableCapabilities bstackOptions = new MutableCapabilities();
            bstackOptions.setCapability("os", "Windows");
            bstackOptions.setCapability("osVersion", "11");
            bstackOptions.setCapability("sessionName", "Checkout flow - smoke");
            bstackOptions.setCapability("buildName", "web-portal-2026.01.27");
           // bstackOptions.setCapability("local", "false");
            bstackOptions.setCapability("seleniumVersion", "4.39.0");
            // Put vendor options under "bstack:options"
            chromeOptions.setCapability("bstack:options", bstackOptions);
        WebDriver driver = new ChromeDriver(chromeOptions);
        //WebDriver driver = new EdgeDriver(edgeOptions);

//            URL hub = new URL("https://oauth-mouneshwar.mallikarjun-aee07:b2f8038b-9146-4017-9574-3b00ee4b850c@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
//            RemoteWebDriver driver = new RemoteWebDriver(hub, chromeOptions);

        driver.get("https://saucelabs.com/");
        System.out.println("Title of the page::"+driver.getTitle());

        driver.quit();
//            try {
//
//            } finally {
//
//            }
        }
    }
