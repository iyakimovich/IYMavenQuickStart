package org.innay.hw6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CRMTestPageFactory {

    private static ChromeOptions chromeOptions;
    private WebDriver driver;
    private WebDriverWait wait;

    private CRMMainPageFactory crmPageFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(CRMTestPageFactory.class);

    @BeforeAll
    public static void runBeforeAllTests() {
        LOGGER.info("runBeforeAllTests() - started");
        WebDriverManager.chromedriver().setup();

        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");

        WebDriverManager.chromedriver().setup();
        LOGGER.info("runBeforeAllTests() - done");
    }

    @BeforeEach
    public void runBeforeEach() {
        LOGGER.info("runBeforeEach() - started");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1400, 900));

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://crm.geekbrains.space/user/login");

        crmPageFactory = new CRMMainPageFactory(driver, wait);
        crmPageFactory.login("Applanatest1", "Student2020!");
    }

    @AfterEach
    public void runAfterEach() {
        LOGGER.info("runAfterEach() - started");
        if (driver != null) {
            driver.close();
        }
        LOGGER.info("runAfterEach() - done");
    }

    @Test
    public void CRMCreateContactTest() {
        LOGGER.info("Test Case: CRMCreateContactTest() - started!");

        crmPageFactory.createContact();
        assertTrue(driver.getCurrentUrl().contains("/contact/"));

        LOGGER.info("Test Case: CRMCreateContactTest() - completed!");

    }

    @Test
    public void CRMCreateProjectTest() {
        LOGGER.info("Test Case: Project Creation - started!");

        crmPageFactory.createProject();
        assertTrue(driver.getCurrentUrl().contains("/project/"));

        LOGGER.info("Test Case: Project Creation - completed!");
    }
}
