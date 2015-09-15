package Pages.RFP_Form;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by sergii.stepanov on 05/08/2015.
 */
public class CoverPageRFP extends MainPageRFP {

    public CoverPageRFP(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(date));
    }

    @FindBy (css = "input[title='Date']")
    private WebElement date;
    @FindBy (css = "input[title='Location:']")
    private WebElement location;
    @FindBy (css = "input[title='(Name)']")
    private WebElement name;
    @FindBy (css = "input[title='(Direct Phone) ex:(XXX)XXX-XXXX']")
    private WebElement directPhone;
    @FindBy (css = "input[title='(Cell Phone) ex:(XXX)XXX-XXXX']")
    private WebElement cellPhone;
    @FindBy (className = "cCheckbox")
    private WebElement checkBxAccept;

    public void fillCoverPage(String text){
        sendKeyIfEquals(date, "", getCurrentDate());
        location.sendKeys(text);
//        name.sendKeys(text);
        sendKeyIfEquals(directPhone, "", "(111)222-3333");
        sendKeyIfEquals(cellPhone, "", "(111)222-3333");
        if (!checkBxAccept.isSelected()){
            checkBxAccept.click();
        }
    }
}
