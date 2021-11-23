package org.innay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class TestCase1
{
    public static void main(String[] args ) {
        System.out.println( "Homework #3 - Test Case 1 - Project Creation" );

        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);

        try {
            driver.get("https://crm.geekbrains.space/user/login");

            WebElement login = driver.findElement(By.id("prependedInput"));
            login.sendKeys("Applanatest1");

            WebElement password = driver.findElement(By.id("prependedInput2"));
            password.sendKeys("Student2020!");

            WebElement enter = driver.findElement(By.id("_submit"));
            enter.submit();

            WebElement mainMenu = driver.findElement(By.xpath("//div[@id='main-menu']"));

            WebElement projectsMenu = mainMenu.findElement(By.xpath("ul/li[3]"));
            projectsMenu.click();

            WebElement myProjectsMenu = mainMenu.findElement(By.xpath("//li[@data-route='crm_project_my']"));
            myProjectsMenu.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
            System.out.println("Test Case: Project Creation - completed successfully!");

        }
        finally {
            driver.quit();
        }
    }
}
