package org.example.robot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotKeysCheck {
    @Test
    public void robotMethods() throws AWTException, InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.browserstack.com/");

        driver.findElement((By.linkText("Get started free"))).click();
        Robot r = new Robot(); //instance of Robot class
           Thread.sleep(3000);
        r.keyPress(KeyEvent.VK_TAB);//Presses TAB button
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_A);//Presses A button
        r.keyRelease(KeyEvent.VK_M);//Presses M button

        r.keyPress(KeyEvent.VK_M);//Presses M button
        r.keyRelease(KeyEvent.VK_M);

    }
}
