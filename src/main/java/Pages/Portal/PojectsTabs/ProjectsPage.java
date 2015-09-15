package Pages.Portal.PojectsTabs;

import Pages.Portal.PortalMainPage;
import framework.GlobalVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by sergii.stepanov on 14/08/2015.
 */
public class ProjectsPage extends PortalMainPage {

    public ProjectsPage(WebDriver driver) {
        super(driver);
        waitForAJAXfinished();
    }

    private String editProjBtnPath=String.format("span[rel='%s']", GlobalVariables.getProjectId());
    @FindBy (className = "ui-pg-selbox")
    private WebElement pageNmbItems;
    @FindBy (id = "next_projectsPager")
    private WebElement nextPageBtn;

    @FindBy (css = "td[aria-describedby='projectsList_projectNumber']")
    private List<WebElement> projectNmbList;
    @FindBy (css = "td[aria-describedby='projectsList_agreement.agreementNumber']")
    private List<WebElement> agreementNmbList;
    @FindBy (css = "td[aria-describedby='projectsList_createdDate']")
    private List<WebElement> createdDateList;
    @FindBy (css = "td[aria-describedby='projectsList_projectManager']")
    private List<WebElement> projOwnerList;
    @FindBy (css = "td[aria-describedby='projectsList_pct']")
    private List<WebElement> pctList;
    @FindBy (css = "td[aria-describedby='projectsList_projectStatus']")
    private List<WebElement> statusList;
    @FindBy (css = "td[aria-describedby='projectsList_activityStatus']")
    private List<WebElement> stateList;

    //region filters fields
    @FindBy (id = "projectNumber")
    private WebElement projNmbFilter;
    @FindBy (id = "agreementNumber")
    private WebElement agreementNmbFilter;
    @FindBy (id = "projectStatus")
    private WebElement projectStatusFilter;
    @FindBy (id = "activityStatus")
    private WebElement stateFilter;
    @FindBy (id = "projectManager")
    private WebElement projectOwnerFilter;
    @FindBy (id = "pct")
    private WebElement pctFilter;
    @FindBy (id = "search")
    private WebElement searchBtn;
    @FindBy (id = "clear")
    private WebElement resetBtn;
    //endregion

    public ProjectsInnerMainPage openProject(){
        if(findProjectAtTheList()) {
            driver.findElement(By.cssSelector(editProjBtnPath)).click();
            return new ProjectsInnerMainPage(driver);
        }else throw new RuntimeException("ERROR! Cant find a project!");
    }

    private boolean findProjectAtTheList(){
        Select pageSelect=new Select(pageNmbItems);
        pageSelect.selectByValue("30");
        waitForAJAXfinished();
        while (!nextPageBtn.getAttribute("class").contains("disabled")) {
            if (isElementPresent(By.cssSelector(editProjBtnPath))) {
                return true;
            }else {
                nextPageBtn.click();
                waitForAJAXfinished();
            }
        }
        if (isElementPresent(By.cssSelector(editProjBtnPath))){
            return true;
        }else {
            return false;
        }
    }

    private void makeSearch(WebElement searchByWebElement, String searchText){
        resetBtn.click();
        waitForAJAXfinished();
        searchByWebElement.sendKeys(searchText);
        searchBtn.click();
        waitForAJAXfinished();
    }

    public List<WebElement> filterByProjNmb(String projNmb){
        makeSearch(projNmbFilter,projNmb);
        return projectNmbList;
    }

    public List<WebElement> filterByAgreementNmb(String agreementNmb){
       makeSearch(agreementNmbFilter, agreementNmb);
        return agreementNmbList;
    }

    public List<WebElement> filterByProjectOwner(String projOwnerName){
        makeSearch(projectOwnerFilter,projOwnerName);
        return projOwnerList;
    }

    public List<WebElement> filterByPCT(String pctName){
        makeSearch(pctFilter, pctName);
        return pctList;
    }

    public List<WebElement> filterByStatus(String projStatus){
        resetBtn.click();
        waitForAJAXfinished();
        Select selectStatus=new Select(projectStatusFilter);
        selectStatus.selectByVisibleText(projStatus);
        searchBtn.click();
        waitForAJAXfinished();
        return statusList;
    }

    public List<WebElement> filterByState(String projState){
        resetBtn.click();
        waitForAJAXfinished();
        Select selectStatus=new Select(stateFilter);
        selectStatus.selectByVisibleText(projState);
        searchBtn.click();
        waitForAJAXfinished();
        return stateList;
    }
}
