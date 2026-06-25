package org.example.fileupload;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploadUsingSendKeys {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Maximize the browser window
            driver.manage().window().maximize();
            // Navigate to the specified URL
            driver.get("https://demoqa.com/upload-download");

            // Find the "Choose File" button element on the webpage
            WebElement chooseFile = driver.findElement(By.xpath("//input[@id='uploadFile']"));
            //chooseFile.sendKeys("C:\\Users\\2445477\\IdeaProjects\\DemoQA_Project\\CreateWorkbook.xlsx");
            chooseFile.sendKeys(System.getProperty("user.dir") + "\\CreateWorkbook.xlsx");
            Thread.sleep(2000);

            List<WebElement> uploadedPath = driver.findElements(By.cssSelector("p#uploadedFilePath"));

            if (uploadedPath.size() > 0) {
                System.out.println("File Uploaded successfully");
            }
            else{
                System.out.println("File is not uploaded");
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            //driver.quit();
        }
    }
}
