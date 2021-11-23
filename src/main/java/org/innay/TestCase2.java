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


public class TestCase2
{
    public static void main(String[] args ) {
        System.out.println( "Homework #3 - Test Case 2 - Contact Creation" );

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
            System.out.println("Test Case: Contact Creation - completed successfully!");

        }
        finally {
            driver.quit();
        }
    }
}
