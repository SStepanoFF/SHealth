package portalTests;

import Pages.Portal.PojectsTabs.ProjectScopePage;
import Pages.Portal.PojectsTabs.ProjectsInnerMainPage;
import Pages.Portal.PojectsTabs.ProjectsPage;
import Pages.Portal.PojectsTabs.RFPPage;
import Pages.Portal.PortalMainPage;
import framework.GlobalVariables;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import setup.BaseTest;

/**
 * Created by sergii.stepanov on 20/08/2015.
 */
public class FirstLaunchRFPTest extends BaseTest {

    private PortalMainPage portalMainPage;
    private RFPPage rfpPage;
    private ProjectScopePage projectScopePage;

    @Test(priority = 1)
    private void loginPortalTest(){
        loginPortalTest(GlobalVariables.getPmMail(), GlobalVariables.PORTAL_USER_PASS);
    }

    @Test (priority = 2)
    private void openProjectScopePageTest(){
        portalMainPage=PortalMainPage.getInstance(driver);
        ProjectsPage projectsPage=portalMainPage.openProjectsPage();
        ProjectsInnerMainPage projectsInnerMainPage= projectsPage.openProject();
        rfpPage=projectsInnerMainPage.openRfpTab();
        projectScopePage=rfpPage.clickLaunchBtn();
    }

    @Test (priority = 3)
    private void fillAndSaveScopeTest(){
        projectScopePage.fillAndSaveTheScope();
        projectScopePage.moveBack();
        Assert.assertTrue(rfpPage.getLaunchBtn().isDisplayed());
        rfpPage.clickLaunchBtn();
        for (WebElement element: projectScopePage.getTextFieldsList()){
            Assert.assertTrue(element.getText()!=null);
        }
    }

    @Test (priority = 4)
    private void launchRFPTest(){
        projectScopePage.launchRFP();
    }

}
