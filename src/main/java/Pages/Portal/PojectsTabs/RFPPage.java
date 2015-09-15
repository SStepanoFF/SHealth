package Pages.Portal.PojectsTabs;

import Pages.Portal.PortalMainPage;
import framework.GlobalVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DataBase;

import java.util.List;

/**
 * Created by sergii.stepanov on 14/08/2015.
 */
public class RFPPage extends PortalMainPage {

    public RFPPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(searchBtn));
    }

    @FindBy (css = "a[title='View vendor']")
    private List<WebElement> vendorViewList;
    @FindBy (id = "launchFRPBtn")
    private WebElement launchBtn;
    public WebElement getLaunchBtn(){
        return launchBtn;
    }
    @FindBy (id = "search")
    private WebElement searchBtn;

    private WebElement findVendor(){
        String vendorID= DataBase.executeSQLQuery("Select vendor_id from vendors where vendor_email='"+GlobalVariables.VENDOR_MAIL+"'","vendor_id");
        WebElement vendorWebElem=null;
        for (WebElement element:vendorViewList){
            if (element.getAttribute("onclick").contains(vendorID)){
                vendorWebElem=element;
                break;
            }
        }
        return vendorWebElem;
    }

    public VendorsRFPPage openVendorsRFPPage(){
        WebElement element;
        if ((element =findVendor())!=null){
            element.click();
            return new VendorsRFPPage(driver);
        }else {
            throw new RuntimeException("Can't find vendor at the list!");
        }
    }

    public ProjectScopePage clickLaunchBtn(){
        launchBtn.click();
        return new ProjectScopePage(driver);
    }
}
