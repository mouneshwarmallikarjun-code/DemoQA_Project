package brokenlinks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinksSingleClass {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            // Open real-time website
            driver.get("https://www.amazon.in");
            driver.manage().window().maximize();

            // Get all links
            List<WebElement> links = driver.findElements(By.tagName("a"));
            System.out.println("Total links found: " + links.size());

            // To avoid duplicate link validation
            Set<String> uniqueLinks = new HashSet<>();

            for (WebElement link : links) {

                String url = link.getAttribute("href");

                // Skip null or empty
                if (url == null || url.isEmpty()) {
                    continue;
                }

                // Skip non-http links
                if (!url.startsWith("http")) {
                    continue;
                }

                // Avoid duplicates
                if (uniqueLinks.contains(url)) {
                    continue;
                }

                uniqueLinks.add(url);

                // Validate link
                try {
                    URL linkUrl = new URL(url);
                    HttpURLConnection connection =
                            (HttpURLConnection) linkUrl.openConnection();

                    connection.setRequestMethod("HEAD");
                    connection.setConnectTimeout(5000);
                    connection.connect();

                    int responseCode = connection.getResponseCode();

                    if (responseCode >= 400) {
                        System.out.println(url + " --> BROKEN (" + responseCode + ")");
                    } else {
                        System.out.println(url + " --> VALID (" + responseCode + ")");
                    }

                } catch (Exception e) {
                    System.out.println(url + " --> ERROR: " + e.getMessage());
                }
            }

            System.out.println("\nTotal unique links checked: " + uniqueLinks.size());

        } finally {
            driver.quit();
        }
    }
}
