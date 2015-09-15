package webDriver;


import utils.CommonOperations;
import utils.Loader;
import framework.GlobalVariables;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public abstract class DriverOperations extends CommonOperations {

    protected WebDriver driver;
    protected static WebDriverWait wait;

    public DriverOperations(WebDriver driver) {
        this.driver=driver;
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(GlobalVariables.GLOBAL_TIMEOUT, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, GlobalVariables.GLOBAL_TIMEOUT);
    }

    public boolean isElementPresent(By element) {
        try {
            driver.manage().timeouts().implicitlyWait(600, TimeUnit.MILLISECONDS);
            if (driver.findElement(element).isDisplayed()) return true;
            else return false;
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(Loader.loadProperty("timeout")), TimeUnit.SECONDS);
        }
    }

    public void waitForAJAXfinished() {
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            }
        });
    }

    public void fillText(List<WebElement> list, String text){
        for (WebElement element:list){
            element.sendKeys(text);
        }
    }
    public void fillTextIfEquals(List<WebElement> list, String equalsText, String setText){
        for (WebElement element:list){
            sendKeyIfEquals(element, equalsText, setText);
        }
    }
    public void sendKeyIfEquals(WebElement element, String equalsText, String setText){
        if (element.getAttribute("value").equals(equalsText)) {
            element.clear();
            element.sendKeys(setText);
        }
    }
}