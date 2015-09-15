package Pages.Portal;

import Pages.Portal.PojectsTabs.ProjectsInnerMainPage;
import Pages.Portal.PojectsTabs.ProjectsPage;
import Pages.Portal.PojectsTabs.RFPPage;
import Pages.Portal.PojectsTabs.VendorsRFPPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webDriver.DriverOperations;

/**
 * Created by sergii.stepanov on 14/08/2015.
 */
public class PortalMainPage extends DriverOperations {

    private static PortalMainPage instance=null;
    public PortalMainPage(WebDriver driver) {
        super(driver);
        instance=this;
        waitForAJAXfinished();
        wait.until(ExpectedConditions.visibilityOf(projectsTab));
    }

    public static PortalMainPage getInstance(WebDriver driver){
        if(instance==null){
            instance=new PortalMainPage(driver);
        }
        return instance;
    }

    @FindBy (id = "projectsMenuItem")
    private WebElement projectsTab;
    @FindBy(id = "my_profileMenuItem")
    private WebElement myProfileTab;

    public ProjectsPage openProjectsPage(){
        projectsTab.click();
        return new ProjectsPage(driver);
    }

    public VendorsRFPPage openVendorRFPPage(){
        ProjectsPage projectsPage=openProjectsPage();
        ProjectsInnerMainPage projectsInnerMainPage= projectsPage.openProject();
        RFPPage rfpPage=projectsInnerMainPage.openRfpTab();
        return rfpPage.openVendorsRFPPage();
    }
}
