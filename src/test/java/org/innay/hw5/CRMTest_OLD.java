package org.innay.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CRMTest_OLD {

    private static ChromeOptions chromeOptions;
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement mainMenu;

    private static final Logger LOGGER = LoggerFactory.getLogger(CRMTest_OLD.class);

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

        WebElement login = driver.findElement(By.id("prependedInput"));
        login.sendKeys("Applanatest1");

        WebElement password = driver.findElement(By.id("prependedInput2"));
        password.sendKeys("Student2020!");

        WebElement enter = driver.findElement(By.id("_submit"));
        enter.submit();

        mainMenu = driver.findElement(By.xpath("//div[@id='main-menu']"));
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

        WebElement projectsMenu = mainMenu.findElement(By.xpath("ul/li[1]"));
        projectsMenu.click();

        WebElement contactsMenu = mainMenu.findElement(By.xpath("//li[@data-route='crm_contact_index']"));
        contactsMenu.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String xPath = "//div[@class='btn-group']/div[@class='pull-left btn-group icons-holder']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

        WebElement createContactButton = mainMenu.findElement(By.xpath(xPath));
        createContactButton.click();

        System.out.println("Test Case: Contact Creation - completed successfully!");

        //Фамилия
        xPath = "//input[@name='crm_contact[lastName]']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).sendKeys("Иванов");

        //Имя
        xPath = "//input[@name='crm_contact[firstName]']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).sendKeys("Иван");

        //Организация
        xPath = "//div[@class='company-container']/div[1]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        WebElement elementCompany = driver.findElement(By.xpath(xPath));
        elementCompany.click();

        xPath = "//*[@id='select2-drop']/ul[@class='select2-results']/li[3]/div";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).click();

        //Должность
        xPath = "//input[@name='crm_contact[jobTitle]']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).sendKeys("Босс");

        //Сохранить и закрыть
        xPath = "//button[@data-action='{\"route\":\"crm_contact_index\"}']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).click();

        wait.until(ExpectedConditions.urlContains("/contact/"));
        LOGGER.info("Test Case: CRMCreateContactTest() - completed!");

    }

    @Test
    public void CRMCreateProjectTest() {
        LOGGER.info("Test Case: Project Creation - started!");

        WebElement projectsMenu = mainMenu.findElement(By.xpath("ul/li[3]"));
        projectsMenu.click();

        WebElement myProjectsMenu = mainMenu.findElement(By.xpath("//li[@data-route='crm_project_my']"));
        myProjectsMenu.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pull-left btn-group icons-holder']/a")));

        WebElement createProjectButton = driver.findElement(By.xpath("//div[@class='pull-left btn-group icons-holder']/a"));
        createProjectButton.click();

        String xPath = "//input[@name='crm_project[name]']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).sendKeys("Inna Y - Project #" + System.currentTimeMillis());

        xPath = "//div[@class='company-container']/div[1]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        WebElement elementCompany = driver.findElement(By.xpath(xPath));
        elementCompany.click();

        xPath = "//*[@id='select2-drop']/ul[@class='select2-results']/li[3]/div";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).click();

        xPath = "//*[@name='crm_project[businessUnit]']";
        WebElement businessUnit = driver.findElement(By.xpath(xPath));
        businessUnit.click();

        xPath = "//*[@name='crm_project[businessUnit]']/option[2]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).click();

        xPath = "//*[@name='crm_project[curator]']";
        driver.findElement(By.xpath(xPath)).click();

        xPath = "//*[@name='crm_project[curator]']/option[4]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).click();

        xPath = "//*[@name='crm_project[rp]']";
        driver.findElement(By.xpath(xPath)).click();

        xPath = "//*[@name='crm_project[rp]']/option[6]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).click();

        xPath = "//*[@name='crm_project[manager]']";
        driver.findElement(By.xpath(xPath)).click();

        xPath = "//*[@name='crm_project[manager]']/option[5]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).click();

        xPath = "//button[@data-action='{\"route\":\"crm_project_index\"}']";
        driver.findElement(By.xpath(xPath)).click();

        wait.until(ExpectedConditions.urlContains("/project/"));
        LOGGER.info("Test Case: Project Creation - completed!");
    }
}
