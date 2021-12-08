package org.innay.hw6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CRMMainPageFactory {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "prependedInput")
    private WebElement inputUserName;

    @FindBy(id = "prependedInput2")
    private WebElement inputUserPassword;

    @FindBy(id = "_submit")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//div[@id='main-menu']")
    private WebElement menuMain;

    @FindBy(xpath = "//input[@name='crm_contact[lastName]']")
    private WebElement inputLastName;

    @FindBy(xpath = "//input[@name='crm_contact[firstName]']")
    private WebElement inputFirstName;

    @FindBy(xpath = "//input[@name='crm_contact[jobTitle]']")
    private WebElement inputJobTitle;

    @FindBy(xpath = "//button[@data-action='{\"route\":\"crm_contact_index\"}']")
    private WebElement buttonSaveContact;

    @FindBy(xpath = "//input[@name='crm_project[name]']")
    private WebElement inputProjectName;

    public CRMMainPageFactory(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void login(String userName, String userPassword) {
        inputUserName.sendKeys(userName);
        inputUserPassword.sendKeys(userPassword);
        buttonSubmit.submit();
    }

    public void createContact() {
        WebElement projectsMenu = menuMain.findElement(By.xpath("ul/li[1]"));
        projectsMenu.click();

        WebElement contactsMenu = menuMain.findElement(By.xpath("//li[@data-route='crm_contact_index']"));
        contactsMenu.click();

        String xPath = "//div[@class='btn-group']/div[@class='pull-left btn-group icons-holder']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

        WebElement createContactButton = menuMain.findElement(By.xpath(xPath));
        createContactButton.click();

        //Фамилия
        inputLastName.sendKeys("Иванов");

        //Имя
        inputFirstName.sendKeys("Иван");

        //Организация
        xPath = "//div[@class='company-container']/div[1]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        WebElement elementCompany = driver.findElement(By.xpath(xPath));
        elementCompany.click();

        xPath = "//*[@id='select2-drop']/ul[@class='select2-results']/li[3]/div";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).click();

        //Должность
        inputJobTitle.sendKeys("Босс");

        //Сохранить и закрыть
        buttonSaveContact.click();
    }

    public void createProject() {
        WebElement projectsMenu = menuMain.findElement(By.xpath("ul/li[3]"));
        projectsMenu.click();

        WebElement myProjectsMenu = menuMain.findElement(By.xpath("//li[@data-route='crm_project_my']"));
        myProjectsMenu.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pull-left btn-group icons-holder']/a")));

        WebElement createProjectButton = driver.findElement(By.xpath("//div[@class='pull-left btn-group icons-holder']/a"));
        createProjectButton.click();

        inputProjectName.sendKeys("Inna Y - Project #" + System.currentTimeMillis());

        String xPath = "//div[@class='company-container']/div[1]";
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

    }
}
