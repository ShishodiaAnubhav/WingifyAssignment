
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginJUnit {
	private WebDriver driver;

	@BeforeEach
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://sakshingp.github.io/assignment/login.html");
    }

	@AfterEach
    public void tearDown() {
        driver.quit();
    }

    // Login Page Tests

	@Test
    public void testValidLogin() {
        login("testUser", "testPassword");
        assertTrue(isLoggedIn());
        System.out.println();
        if (isLoggedIn()) {
            System.out.println("testValidLogin passed");
        } else {
            System.out.println("testValidLogin failed");
        }

    }
	
	@Test
    public void testEmptyUsername() {
        login("", "anyPassword");
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(), 'Username must be present')]"));
        assertEquals("Username must be present", errorMessage.getText());
        if ("Username must be present".equals(errorMessage.getText())) {
            System.out.println("testEmptyUsername passed");
        } else {
            System.out.println("testEmptyUsername failed");
        }
    }

	@Test
    public void testEmptyPassword() {
        login("anyUser", "");
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(), 'Password must be present')]"));
        assertEquals("Password must be present", errorMessage.getText());
        if ("Password must be present".equals(errorMessage.getText())) {
            System.out.println("testEmptyPassword passed");
        } else {
            System.out.println("testEmptyPassword failed");
        }
    }

	@Test
    public void testEmptyUsernameAndPassword() {
        login("", "");
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(), 'Both Username and Password must be present')]"));
        assertEquals("Both Username and Password must be present", errorMessage.getText());

        if ("Both Username and Password must be present".equals(errorMessage.getText())) {
            System.out.println("testEmptyUsernameAndPassword passed");
        } else {
            System.out.println("testEmptyUsernameAndPassword failed");
        }
    }

    // Home Page Tests

	@Test
    public void testAmountColumnSorting() {
        
        login("anyUser", "anyPassword");
        assertTrue(isLoggedIn());
        if (!isLoggedIn()) {
            System.out.println("testAmountColumnSorting failed - login unsuccessful");
            return;
        }

        
        WebElement amountHeader = driver.findElement(By.xpath("//th[@id='amount']"));
        amountHeader.click();

        
        List<WebElement> amountElements = driver.findElements(By.xpath("//td[@class='amount']"));
        List<Double> actualAmounts = new ArrayList<>();
        for (WebElement element : amountElements) {
            actualAmounts.add(Double.parseDouble(element.getText()));
        }

        List<Double> sortedAmounts = new ArrayList<>(actualAmounts);
        Collections.sort(sortedAmounts);

        assertEquals(sortedAmounts, actualAmounts);
        if (sortedAmounts.equals(actualAmounts)) {
            System.out.println("testAmountColumnSorting passed");
        } else {
            System.out.println("testAmountColumnSorting failed");
        }
    }

    private void login(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("log-in"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    private boolean isLoggedIn() {
    	String homeUrl = "https://sakshingp.github.io/assignment/home.html";
        if (driver.getCurrentUrl().equals(homeUrl)) {
            return true;
        } else {
            return false;
        }
    }
    
    @Test
    public void checkLoginButtonFunction() {

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));


        usernameField.sendKeys("anyUsername");
        passwordField.sendKeys("anyPassword");


        WebElement loginButton = driver.findElement(By.id("log-in"));


        loginButton.click();


        String expectedUrl = "https://sakshingp.github.io/assignment/home.html";
        assertEquals(driver.getCurrentUrl(), expectedUrl);
        if (driver.getCurrentUrl().equals(expectedUrl)) {
            System.out.println("Login button function test passed: Redirected to home page.");
        } else {
            System.out.println("Login button function test failed: Did not redirect to home page.");
        }
    }
    
    @Test
    public void checkImageButtonFunction() {
    	
        WebElement imageButton = driver.findElement(By.cssSelector("img[src='img/logo-big.png']"));

        imageButton.click();

        String indexUrl = "https://sakshingp.github.io/assignment/index.html";
        assertEquals(driver.getCurrentUrl(), indexUrl);
        if (driver.getCurrentUrl().equals(indexUrl)) {
            System.out.println("Image button function test passed: Redirected to home page.");
        } else {
            System.out.println("Image button function test failed: Did not redirect to home page.");
        }
    }
    
    @Test
    public void testCheckboxButton() {

        WebElement checkbox = driver.findElement(By.className("form-check-input"));

        boolean initialState = checkbox.isSelected();
        System.out.println("Initial state of checkbox: " + initialState);
        checkbox.click();

        boolean updatedState = checkbox.isSelected();
        System.out.println("Updated state of checkbox: " + updatedState);

        assertEquals(initialState, !updatedState);
        
        if (initialState != updatedState) {
            System.out.println("Checkbox button test passed: Checkbox state toggled successfully.");
        } else {
            System.out.println("Checkbox button test failed: Checkbox state not toggled.");
        }
    }
    
    @Test
    public void testIcon() {

        WebElement facebookIcon = driver.findElement(By.cssSelector("img[src='img/facebook.png']"));
        WebElement twitterIcon = driver.findElement(By.cssSelector("img[src='img/twitter.png']"));
        WebElement linkedInIcon = driver.findElement(By.cssSelector("img[src='img/linkedin.png']"));


        assertTrue(facebookIcon.isDisplayed());
        if (facebookIcon.isDisplayed()) {
            System.out.println("Facebook Icon is displayed.");
        } else {
            System.out.println("Facebook Icon is not displayed.");
        }
        
        assertTrue(twitterIcon.isDisplayed());
        if (twitterIcon.isDisplayed()) {
            System.out.println("Twitter Icon is displayed.");
        } else {
            System.out.println("Twitter Icon is not displayed.");
        }
        
        assertTrue(linkedInIcon.isDisplayed());
        if (linkedInIcon.isDisplayed()) {
            System.out.println("LinkedIn Icon is displayed.");
        } else {
            System.out.println("LinkedIn Icon is not displayed.");
        }
    }

}
