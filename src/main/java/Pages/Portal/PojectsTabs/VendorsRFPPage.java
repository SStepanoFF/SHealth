package Pages.Portal.PojectsTabs;

import Pages.Portal.PortalMainPage;
import Pages.RFP_Form.MainPageRFP;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by sergii.stepanov on 19/08/2015.
 */
public class VendorsRFPPage extends PortalMainPage {

    public VendorsRFPPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(backBtn));
    }

    @FindBy (id = "backBtn")
    private WebElement backBtn;
    @FindBy (xpath = "//a[title='View RFP']//span")
    private List<WebElement> viewRFPBtnList;
    @FindBy (xpath = "//a[@title='Edit RFP']//span[@rel='1']")
    private WebElement editRFPFormBtn;
    @FindBy (xpath = "//a[@title='Launch RFP']//span[@rel='1']")
    private WebElement launchRFPFormBtn;

    @FindBy (className = "cboxIframe")
    private WebElement iframeRFP;
    @FindBy (xpath = "//div[@class='ui-dialog-buttonset']//button[1]")
    private WebElement confirmToLaunchFormToVendorAlert;

    public void fillRFPForm(){
        editRFPFormBtn.click();
        waitForAJAXfinished();
        driver.switchTo().frame(iframeRFP);
        MainPageRFP mainPageRFP=new MainPageRFP(driver);
        mainPageRFP.fillRFPForm("pm");
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
    }

    public void launchRFPToVendor(){
        launchRFPFormBtn.click();
        waitForAJAXfinished();
        confirmToLaunchFormToVendorAlert.click();
        waitForAJAXfinished();
        waitForServerUpdate();
    }

    private void waitForServerUpdate(){
        if (isElementPresent(By.id("ui-id-3"))){  //wait to update server message
            confirmToLaunchFormToVendorAlert.click();
            waitForAJAXfinished();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            launchRFPToVendor();
        }
    }

    public int getCountOfRFPVersion(){
        return viewRFPBtnList.size();
    }
}
