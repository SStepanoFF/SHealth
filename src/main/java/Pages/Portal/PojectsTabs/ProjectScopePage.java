package Pages.Portal.PojectsTabs;

import Pages.Portal.PortalMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by sergii.stepanov on 20/08/2015.
 */
public class ProjectScopePage extends PortalMainPage {

    public ProjectScopePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(launchBtn));
    }

    @FindBy (css = "input[type='text']")
    private List<WebElement> textFieldsList;
    public List<WebElement> getTextFieldsList(){
        return textFieldsList;
    }
    @FindBy (id = "backBtn")
    private WebElement backBtn;
    @FindBy (id = "saveBtn")
    private WebElement saveBtn;
    @FindBy (id = "previewBtn")
    private WebElement previewBtn;
    @FindBy (id = "launchBtn")
    private WebElement launchBtn;
    @FindBy (xpath = "//div[@class='ui-dialog-buttonset']//span")
    private WebElement confirmMessBtn;
    @FindBy (xpath = "//div[@class='ui-dialog-buttonset']//span")
    private List<WebElement> confirmLaunchBtnList;

    public void fillAndSaveTheScope(){
        int i=1;
        for(WebElement element: textFieldsList){
            element.sendKeys("test scope"+i++);
        }
        saveBtn.click();
        waitForAJAXfinished();
        confirmMessBtn.click();
    }

    public void launchRFP(){
        launchBtn.click();
        confirmLaunchBtnList.get(0).click();
        waitForAJAXfinished();
        confirmMessBtn.click();
    }

    public void moveBack(){
        backBtn.click();
    }
}
