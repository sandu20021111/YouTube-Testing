package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.io.FileHandler;
import java.time.Duration;
import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        // Set path to ChromeDriver (change path for your system)
        System.setProperty("webdriver.chrome.driver", "D:\\Browser drivers\\chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            //open youtube
            driver.get("http://www.youtube.com/");
            takeScreenshot(driver, "step1_homepage");
            System.out.println("open youtube");

            //wait until search box is visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.name("search_query"))
            );

            //Search for a video
            searchBox.sendKeys("Songs");
            searchBox.submit();
            takeScreenshot(driver, "step2_searchVideo");
            System.out.println("searched for video");

            //wait until results are visible and click first video
            WebElement firstVideo = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("(//a[@id='video-title'])[3]"))
            );
            firstVideo.click();
            takeScreenshot(driver, "step3_playVideo");
            System.out.println("Playing first video");

            Thread.sleep(17000);

            //wait until play the first video
            WebElement pauseButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("button.ytp-play-button"))
            );
            pauseButton.click();
            takeScreenshot(driver, "step4_pasueVideo");
            System.out.println("Pause Video");
            Thread.sleep(10000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //close browser
            driver.quit();
            System.out.println("Test completed");

        }
    }

    private static void takeScreenshot(WebDriver driver, String stepName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(srcFile, new File("C:\\Users\\Acer\\Desktop\\youtube\\screenshots\\" + stepName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}