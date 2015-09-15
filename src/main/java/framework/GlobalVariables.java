package framework;

import utils.CSVFilesOperations;
import utils.DataBase;
import utils.Loader;

public class GlobalVariables {

    public static final int GLOBAL_TIMEOUT=Integer.parseInt(Loader.loadProperty("timeout"));
    public static final String FILE_PATH =System.getProperty("user.dir")+"\\src\\main\\resources\\DownloadFiles\\";
    public static final String TEMP_FILE_PATH =System.getProperty("user.dir")+"\\src\\main\\resources\\DownloadFiles\\Temp\\";
    public static final String PORTAL_URL=Loader.loadProperty("portalURL");

//    public static String VENDOR_URL;
    public static final String VENDOR_MAIL= CSVFilesOperations.getCellValue("Project data", "gAgreementVendorEmail", 1);
    public static final String PORTAL_USER_PASS=Loader.loadProperty("portalUserPass");

    private static String PM_MAIL = null;
    public static String getPmMail() {
        if(PM_MAIL==null){
            PM_MAIL=Loader.loadProperty("pmMail");
        }
        return PM_MAIL;
    }
    public static void setPmMail(String pmMail){
        PM_MAIL=pmMail;
        Loader.updateProperty("pmMail", pmMail);
    }


    private static String PROJECT_NUM=null;
    public static String getProjectNum() {
        if (PROJECT_NUM==null){
            PROJECT_NUM=Loader.loadProperty("projectNmb");

        }
        return PROJECT_NUM;
    }
    public static void setProjectNum(String projectNum) {
        Loader.updateProperty("projectNmb", projectNum);
        PROJECT_NUM=Loader.loadProperty("projectNmb");
    }

    private static String PROJECT_ID=null;
    public static String getProjectId(){
        if(PROJECT_ID==null){
            setProjectId();
        }
        return PROJECT_ID;
    }
    public static void setProjectId(){
        PROJECT_ID= DataBase.executeSQLQuery("Select project_id from projects where project_number="+getProjectNum(),"project_id");
    }
}
