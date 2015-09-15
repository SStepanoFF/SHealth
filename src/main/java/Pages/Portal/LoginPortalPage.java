package Pages.Portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webDriver.DriverOperations;

/**
 * Created by sergii.stepanov on 19/08/2015.
 */
public class LoginPortalPage extends DriverOperations {

    public LoginPortalPage(WebDriver driver) {
        super(driver);
        waitForAJAXfinished();
        wait.until(ExpectedConditions.visibilityOf(loginField));
    }

    @FindBy (css = "input[type='text']")
    private WebElement loginField;
    @FindBy (css = "input[type='password']")
    private WebElement passField;
    @FindBy (css = "input[type='submit']")
    private WebElement loginBtn;

    public PortalMainPage loginPortalTest(String login, String passw){
        loginField.sendKeys(login);
        passField.sendKeys(passw);
        loginBtn.click();
        return PortalMainPage.getInstance(driver);
    }
}
