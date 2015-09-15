package Pages.RFP_Form;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by sergii.stepanov on 06/08/2015.
 */
public class ScopeOfServicesPageRFP extends MainPageRFP {

    public ScopeOfServicesPageRFP(WebDriver driver) {
        super(driver);
        waitForAJAXfinished();
//        wait.until(ExpectedConditions.visibilityOf(checkBxsList.get(0)));
    }

    @FindBy (css = "input[type='checkbox']")
    private List<WebElement> checkBxsList;
    @FindBy (xpath = ".//*[@id='tblconstruction_docs_header']//td[1]//input[@type='text']")
    private List<WebElement> numberOfCommentList;
    @FindBy (css = "iframe.sutterWYSIWYG")
    private List<WebElement> commentFieldList;
    @FindBy (xpath = ".//*[@id='tblconstruction_docs_header']//td[3]//input[@type='text']")
    private List<WebElement> dateList;
    @FindBy (xpath = ".//*[@id='tblconstruction_docs_header']//td[4]//input[@type='text']")
    private List<WebElement> revisDateList;

    public void fillScopeOfServicesPage(String text){
        for (WebElement element:checkBxsList){
            if (!element.isSelected()){
                element.click();
            }
        }
        plusBtnClick();
        fillTextIfEquals(numberOfCommentList, "0", "7");
        fillText(commentFieldList, text);
        fillTextIfEquals(dateList, "", getCurrentDate());
        fillTextIfEquals(revisDateList, "", getCurrentDate());
        uploadFile(text);
    }

}
