package portalTests;

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
 * Created by sergii.stepanov on 21/08/2015.
 */
public class ProjectSearchTest extends BaseTest {

    private PortalMainPage portalMainPage;
    private ProjectsPage projectsPage;

    @Test(priority = 1)
    private void loginPortalTest(){
        loginPortalTest(GlobalVariables.getPmMail(), GlobalVariables.PORTAL_USER_PASS);
    }

    @Test (priority = 2)
    private void openProjectsPageTest(){
        portalMainPage= PortalMainPage.getInstance(driver);
        projectsPage=portalMainPage.openProjectsPage();
    }

    @Test (priority = 3)
    private void projectNmbFilterTest(){
        String findText="976";
        for (WebElement element: projectsPage.filterByProjNmb(findText) ){
            Assert.assertTrue(element.getAttribute("title").contains(findText));
        }
    }

    @Test (priority = 4)
    private void agreementNmbFilterTest(){
        String findText="15";
        for (WebElement element: projectsPage.filterByAgreementNmb(findText) ){
            Assert.assertTrue(element.getAttribute("title").contains(findText));
        }
    }

    @Test (priority = 5)
    private void statusFilterTest(){
        String findText="New Project";
        for (WebElement element: projectsPage.filterByStatus(findText) ){
            Assert.assertTrue(element.getAttribute("title").contains(findText));
        }
    }

    @Test (priority = 6)
    private void stateFilterTest(){
        String findText="Active Only";
        for (WebElement element: projectsPage.filterByState(findText)) {
            Assert.assertTrue(element.getAttribute("title").toLowerCase().contains("active"));
        }
    }

    @Test (priority = 7)
    private void projectOwnerFilterTest(){
        String findText="p";
        for (WebElement element: projectsPage.filterByProjectOwner(findText)) {
            Assert.assertTrue(element.getAttribute("title").toLowerCase().contains(findText.toLowerCase()));
        }
    }

    @Test (priority = 8)
    private void pctFilterTest(){
        String findText="test";
        for (WebElement element: projectsPage.filterByPCT(findText)) {
            Assert.assertTrue(element.getAttribute("title").toLowerCase().contains(findText.toLowerCase()));
        }
    }
}
