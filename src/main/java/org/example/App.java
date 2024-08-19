package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("https://pagination.js.org/");
            Thread.sleep(2000);
            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"));
            List<WebElement> pages = driver.findElements(By.xpath("//div[@class='paginationjs-pages']/ul/li"));
            System.out.println(elements.get(6).getText());
            pages.get(8).click();
            wait.until(ExpectedConditions.stalenessOf(elements.get(6)));
            elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"));
            System.out.println(elements.get(6).getText());
            Assert.assertEquals(driver.getTitle(), "Pagination.js | Home");


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
