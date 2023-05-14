package justeattakeaway;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase2 {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver 
        System.setProperty("webdriver.chrome.driver", "//C:\\\\Users\\\\Local_Admin\\\\Downloads\\\\chromedriver_win32\\\\chromedriver.exe");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Just Eat careers website
        driver.get("https://careers.justeattakeaway.com/global/en/home");
        
        /*wait for the pop-up to come
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());

        // Switch to the pop-up
        Alert alert = driver.switchTo().alert();

        // Accept the pop-up as allow
        alert.accept();*/

        // Click on the (Search for Job Title) dropdown and select (Sales)
        WebElement jobTitleDropdown = driver.findElement(By.cssSelector(".job-search__category-select .select2-selection__arrow"));
        jobTitleDropdown.click();
        WebElement salesOption = driver.findElement(By.cssSelector("li[id$='sales']"));
        salesOption.click();

        // Scroll to the (Refine your search) section
        WebElement refineSection = driver.findElement(By.cssSelector(".job-search__refine"));
        refineSection.click();

        // Verify that the section (Sales) is selected and search results number is matching
        WebElement category = driver.findElement(By.cssSelector("input[name='category'][value='sales']"));
        if (category.isSelected()) {
            System.out.println("Category \"Sales\" is selected");
        } else {
            System.out.println("Category \"Sales\" is not selected");
        }
        WebElement searchCount = driver.findElement(By.cssSelector(".job-search__count"));
        int count = Integer.parseInt(searchCount.getText().replaceAll("\\D", ""));
        if (count > 0) {
            System.out.println("Search results number is " + count);
        } else {
            System.out.println("Search results number is 0");
        }

        // Filter the search to Germany
        WebElement germanyCheckbox = driver.findElement(By.cssSelector("input[value='Germany']"));
        germanyCheckbox.click();

        // Wait for the search results to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("/search"));

        // Verify that the no of search results is matching and section is (Sales) on all results
        searchCount = driver.findElement(By.cssSelector(".job-search__count"));
        int newCount = Integer.parseInt(searchCount.getText().replaceAll("\\D", ""));
        if (newCount == count) {
            System.out.println("Number of search results is matching");
        } else {
            System.out.println("Number of search results is not matching");
        }
        boolean categoryMatches = true;
        for (int i = 1; i <= newCount; i++) {
            WebElement jobCard = driver.findElement(By.cssSelector(".job-search__list .job-card:nth-of-type(" + i + ")"));
            WebElement jobTitle = jobCard.findElement(By.cssSelector(".job-card__title"));
            if (!jobTitle.getText().contains("Sales")) {
                categoryMatches = false;
                break;
            }
        }
        if (categoryMatches) {
            System.out.println("Category is \"Sales\" on all results");
        } else {
            System.out.println("Category is not \"Sales\" on all results");
        }

        // Close the browser
        driver.quit();
    }
}