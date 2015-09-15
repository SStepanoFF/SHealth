package utils;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import framework.GlobalVariables;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by sergii.stepanov on 01/07/2015.
 */
public class CSVFilesOperations {

    private static boolean setProjNumb=false;
    private static String projNmb=null;
    private static String agreementNmb=null;

    public static void createCSVFile(String fileName){
        if (!setProjNumb){
            projNmb =getProjectNumberFromDB();
            GlobalVariables.setPmMail(getCellValue("Project data", "pjt_mgr_email", 1));
            setProjNumb=true;
            agreementNmb=getCellValue("Project data", "gAreementID",1);
            agreementNmb=agreementNmb.substring(0,agreementNmb.length()-2);
        }
        DateFormat dateFormat=new SimpleDateFormat("MMddyyyy");
        Date date=new Date();
        CSVWriter writer=null;
        CSVReader reader = null;
        int cellNmb=0;
        try {
            reader = new CSVReader(new FileReader(GlobalVariables.FILE_PATH+fileName+".csv"), ',');
            List<String[]> csvBody = reader.readAll();
            for (int i=0;i<csvBody.get(0).length;i++) {
                if(csvBody.get(0)[i].equals("pjt_number") || csvBody.get(0)[i].equals("Project Number")){
                    cellNmb=i;
                }
            }
            for (int i = 1; i<csvBody.size(); i++) {
                    csvBody.get(i)[cellNmb]= projNmb;
                    String filePath=GlobalVariables.TEMP_FILE_PATH+ projNmb+"_" +agreementNmb+"_"+fileName+"_"+ dateFormat.format(date) +".csv";
                    writer = new CSVWriter(new FileWriter(filePath), ',');
                    writer.writeAll(csvBody);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(fileName+" was not found!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(fileName+" was not created!");
        } finally {
            try {
                writer.flush();
                writer.close();
                reader.close();
                System.out.println(fileName+"_"+ projNmb +".csv was created!");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(fileName+" was not created!");
            }
        }
    }

    public static String getCellValue(String fileName, String cellName, int cellNmb){
        CSVReader reader = null;
        int cellNmb2=0;
        String res=null;
        try {
            reader = new CSVReader(new FileReader(GlobalVariables.FILE_PATH+fileName+".csv"), ',');
            List<String[]> csvBody = reader.readAll();
            for (int i=0;i<csvBody.get(0).length;i++) {
                if(csvBody.get(0)[i].equals(cellName)){
                    cellNmb2=i;
                }
            }
            res= csvBody.get(cellNmb)[cellNmb2];
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    private static String getProjectNumberFromDB(){
        int projNumb=new Random().nextInt(999);
        int curYear= Calendar.getInstance().get(Calendar.YEAR);
        while (true) {
            try {
                DataBase.executeSQLQuery("SELECT project_number from projects WHERE project_number=" + projNumb+"."+curYear, "project_number" );
                projNumb++;
            } catch (RuntimeException ex) {
                break;
            }
        }
        GlobalVariables.setProjectNum(String.format("%s.%s",projNumb,curYear));
        return projNumb+"."+curYear;
    }

    public static final void deleteFiles(){
        for (File file : new File(GlobalVariables.TEMP_FILE_PATH).listFiles()) {
            file.delete();
            System.out.println(String.format("File %s was deleted!",file.getName()));
        }
//        try {
//            FileUtils.cleanDirectory(new File(GlobalVariables.TEMP_FILE_PATH));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
