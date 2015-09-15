import utils.CSVFilesOperations;
import utils.SFTPOperations;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by sergii.stepanov on 04/08/2015.
 */
public class CreateProjectTest {

    @BeforeSuite
    public void setUpTest() {
    }

    @Test (priority = 1)
    private void deleteTempFiles(){
        CSVFilesOperations.deleteFiles();
    }

    @Test (priority = 2)
    private void createProjectsFileTest(){
        CSVFilesOperations.createCSVFile("Owners program");
        CSVFilesOperations.createCSVFile("Project data");
        CSVFilesOperations.createCSVFile("Project Schedule");
    }

    @Test (priority = 3, enabled = true)
    private void downloadFilesToSFTPTest(){
        SFTPOperations.downloadFilesToSFTP();
    }
}
