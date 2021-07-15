import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertTrue;

public class GoogleSearchImageTest {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    public void verifyThatImageTabContainsImage() throws IOException {
        WebElement searchInput = driver.findElement(xpath("//input[@class='gLFyf gsfi']"));
        searchInput.sendKeys("kettle", Keys.ENTER);
        WebElement searchByImageTab = driver.findElement(xpath("//a[@data-hveid='CAIQAw']"));
        searchByImageTab.click();
        WebElement firstResult = driver.findElement(xpath("//img"));
        assertTrue(firstResult.isDisplayed());

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("search-results.png"));
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}