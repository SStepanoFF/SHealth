package Pages.Portal.PojectsTabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by sergii.stepanov on 14/08/2015.
 */
public class ProjectsInnerMainPage extends ProjectsPage {

    public ProjectsInnerMainPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(projectDetailsTab));
    }

    @FindBy (id = "ui-id-1")
    private WebElement projectDetailsTab;
    @FindBy(id = "ui-id-2")
    private WebElement rfpTab;
    @FindBy(id = "ui-id-3")
    private WebElement exhibitTab;

    public RFPPage openRfpTab(){
        rfpTab.click();
        return new RFPPage(driver);
    }
}
