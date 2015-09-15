package setup;

import Pages.Portal.LoginPortalPage;
import framework.GlobalVariables;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import utils.DataBase;
import webDriver.Driver;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


public class BaseTest {

     public WebDriver driver;

    @BeforeSuite
    public void setUpTest(){
        driver = Driver.getInstance();
//        driver.manage().window().maximize(); //setSize(new Dimension(1500,1080));
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
   }

    @AfterMethod
    public void takeScreenShot(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {     //условие делать скриншот если тест FAIL
            DateTime dt = new DateTime();
            DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd_MM_yyyy_HH_mm");
            String resultDateTime = dateFormat.print(dt);
//            Report.log(++step, "Test: " + result.getTestContext().getCurrentXmlTest().getName()
//                    + "_" + result.getName(), Report.State.PASS);
            File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File outputDir = new File(result.getTestContext().getOutputDirectory());
            File saved = new File(System.getProperty("user.dir") + "\\Screenshots\\", resultDateTime + "_" + result.getTestContext().getCurrentXmlTest().getName() + "_" + result.getName() + ".png");
            FileUtils.copyFile(f, saved);
        }
    }

    public void loginPortalTest(String name, String pass){
        this.driver.navigate().to(GlobalVariables.PORTAL_URL);
        LoginPortalPage loginPortalPage=new LoginPortalPage(driver);
        loginPortalPage.loginPortalTest(name, pass);
    }

    public String getVendorUrlFromDB(){
//        SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
//        SAXParser saxParser=null;
//        SAXPars saxPars=new SAXPars();
//        String email= DataBase.executeSQLQuery("SELECT body FROM emails WHERE emailId=(SELECT MAX(emailId) from emails WHERE sentTo='"+ GlobalVariables.VENDOR_MAIL+"' AND body LIKE '%href%')","body");
//            try {
//                saxParser=saxParserFactory.newSAXParser();
//                saxParser.parse(email,saxPars);
//            } catch (ParserConfigurationException e) {
//                e.printStackTrace();
//            } catch (SAXException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        String email= DataBase.executeSQLQuery("SELECT body FROM emails WHERE emailId=(SELECT MAX(emailId) from emails WHERE sentTo='"+ GlobalVariables.VENDOR_MAIL+"' AND body LIKE '%href%')","body");
        email.split("href");
        return "";

    }

    @Test
    private void test(){
        System.out.println(getVendorUrlFromDB());
    }

    @DataProvider(name = "surveyAnswerDataProvider")
    public Object[][] provideData(Method method) {
        Object[][] result = new Object[][]{
            //APAC------
                {8, true},
                {8, false},
                {8, true},
                {8, false},
                {8, true},
                {8, false},
                {8, true},
                {8, false},
             //----------
                {2, true},
                {2, false},
                {2, true},
                {2, false},
                {6, true},
                {6, false},
                {8, true},
                {8, false},
                {8, true},
                {8, false}
        };
        return result;
    }
}

//class SAXPars extends DefaultHandler{
//    private String parsElement = "";
//    private String vendorUrl=null;
//    public String getVendorUrl(){
//        return vendorUrl;
//    }
//
//    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
//        parsElement = qName;
//    }
//
//    public void characters(char[] ch, int start, int length) throws SAXException {
//        if (parsElement.equals("a")) {
//            vendorUrl = (new String(ch, start, length));
//        }
//    }
//}
