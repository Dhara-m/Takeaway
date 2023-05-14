package justeattakeaway;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestCase1 {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "//C:\\Users\\Local_Admin\\Downloads\\chromedriver_win32\\chromedriver.exe");

        // Create a new instance of the chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Just Eat careers website
        driver.get("https://careers.justeattakeaway.com/global/en/home");
        
      /*wait for the pop-up to come
        WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        popupWait.until(ExpectedConditions.alertIsPresent());

        // Switch to the pop-up
        Alert alert = driver.switchTo().alert();

        // Accept the pop-up as allow
        alert.accept();*/
      

        // Find the search field and enter the job title "Test"
        WebElement searchField = driver.findElement(By.id("keywordSearch"));
        searchField.sendKeys("Test");

        // Click the search button
        WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
        searchButton.click();
    

        // Wait for the search results to load 
        WebDriverWait resultsWait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // resultsWait.until(ExpectedConditions.urlContains("/search"));
        resultsWait.
        // Verify that the search results' locations vary
        WebElement location = driver.findElement(By.cssSelector(".job-search__list .job-card__location"));
        String firstLocation = location.getText();
        boolean locationsVary = false;
        while (!locationsVary) {
            driver.navigate().refresh();
            resultsWait.until(ExpectedConditions.urlContains("/search"));
            location = driver.findElement(By.cssSelector(".job-search__list .job-card__location"));
            String newLocation = location.getText();
            if (!newLocation.equals(firstLocation)) {
                locationsVary = true;
            }
        }
        System.out.println("Search results' locations vary");

        // Filter the search to Netherlands
        WebElement netherlandsCheckbox = driver.findElement(By.cssSelector("input[value='Netherlands']"));
        netherlandsCheckbox.click();

        // Wait for the search results to load
        resultsWait.until(ExpectedConditions.urlContains("/search"));

        // Verify that the search result location is Netherlands only
        location = driver.findElement(By.cssSelector(".job-search__list .job-card__location"));
        String locationText = location.getText();
        if (locationText.equals("Netherlands")) {
            System.out.println("Search results' location is Netherlands only");
        } else {
            System.out.println("Search results' location is not Netherlands only");
        }

        // Close the browser
        driver.quit();
    }
}