package portalTests;


import Pages.Portal.LoginPortalPage;
import Pages.Portal.PojectsTabs.VendorsRFPPage;
import Pages.Portal.PortalMainPage;
import framework.GlobalVariables;
import org.junit.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;

/**
 * Created by sergii.stepanov on 19/08/2015.
 */
public class RFPPortalOperationTest extends BaseTest {

    private PortalMainPage portalMainPage;
    private VendorsRFPPage vendorsRFPPage;

    @Test (priority = 1)
    private void loginPortalTest(){
        loginPortalTest(GlobalVariables.getPmMail(), GlobalVariables.PORTAL_USER_PASS);
    }

    @Test (priority = 2)
    private void openVendormTest(){
        portalMainPage=PortalMainPage.getInstance(driver);
        vendorsRFPPage=portalMainPage.openVendorRFPPage();
    }

    @Test (priority = 3, enabled = true)
    private void submitRFPFormByPortalUserTest(){
        vendorsRFPPage.fillRFPForm();
    }

    @Test (priority = 4)
    private void launchRFPToVendorTest(){
        int countRFPVersion=vendorsRFPPage.getCountOfRFPVersion();
        vendorsRFPPage.launchRFPToVendor();
        int countAfter=vendorsRFPPage.getCountOfRFPVersion();
        System.out.println(String.format("countVersion before: %s   after: %s",countRFPVersion,countAfter));
        Assert.assertTrue(countRFPVersion<countAfter);
    }

}
