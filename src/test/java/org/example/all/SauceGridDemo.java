package org.example.all;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.Map;

public class SauceGridDemo {
    public static void main(String[] args) throws Exception {
        String username   = System.getenv("oauth-mouneshwar.mallikarjun-aee07");   // set env vars locally/CI
        String accessKey  = System.getenv("b2f8038b-9146-4017-9574-3b00ee4b850c");

        String sauceUrl  = "https://" + username + ":" + accessKey + "@ondemand.saucelabs.com/wd/hub";
        ChromeOptions options = new ChromeOptions();
       // RemoteWebDriver driver = new RemoteWebDriver(new URL(sauceUrl), options); // options = ChromeOptions/FirefoxOptions


        options.setPlatformName("Windows 11");
        options.setBrowserVersion("142");
        // Recommended W3C capabilities (Selenium 4) for Sauce metadata:
        options.setCapability("sauce:options", Map.of(
                "name", "Demo - Login flow",
                "build", "build-123",
                "seleniumVersion", "4.14.0"
        ));

        RemoteWebDriver driver = new RemoteWebDriver(new URL(sauceUrl), options);
        try {
            driver.get("https://www.saucedemo.com/");
            // ... your assertions ...
        } finally {
            driver.quit();
        }
    }
}

