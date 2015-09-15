import framework.GlobalVariables;
import org.apache.commons.io.FileUtils;
import utils.CSVFilesOperations;
import utils.CommonOperations;
import utils.DataBase;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
* Created by sergii.stepanov on 20.01.2015.
*/
public class Test {

    public static void main(String []args) throws Exception {
//        Map<Integer, HashCode> collection = new HashMap<Integer, HashCode>();
//        for (Integer i = 5; i > 0; i--) {
//            collection.put(i, HashCode.fromInt(i.hashCode()));
////            collection.put(i.hashCode(), i);
//        }
//        Iterator<Integer> iterator=collection.keySet().iterator();
//        while (iterator.hasNext()){
//            if(iterator.next()==1){
//                System.out.println("Iterator getClass: "+iterator.getClass());
//                System.out.println("Iterator.next getClass: "+iterator.next().getClass());
//                iterator.remove();
//            }
//        }

//        File file = new File(GlobalVariables.TEMP_FILE_PATH+"test.txt");
//        if (!file.exists()){
//            file.createNewFile();
//        }
//        System.out.println(DataBase.executeSQLQuery("Select project_id from projects where project_number=1", "project_id"));
//
//        List <String> fileLineList=FileUtils.readLines(new File(GlobalVariables.FILE_PATH+"test.txt"));
//        Collections.sort(fileLineList,new Compare() );
//        PrintWriter printWriter=new PrintWriter(new File(GlobalVariables.FILE_PATH+"res.txt"));
//        for (String line:fileLineList) {
//            printWriter.write(line+"\n");
//        }
//        printWriter.close();

//        byte b=127;
//        String email= DataBase.executeSQLQuery("SELECT body FROM emails WHERE emailId=(SELECT MAX(emailId) from emails WHERE sentTo='"+ GlobalVariables.VENDOR_MAIL+"' AND body LIKE '%href%')","body");
//        String res=CommonOperations.parsTextByRegEx(email, "http(.*?)'");
//        System.out.println(res);

        System.out.println("enter ");
        Scanner scanner=new Scanner(System.in);
        System.out.println("res "+scanner.nextBigDecimal());

    }


}
 class Compare implements Comparator<String> {

    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}

