package utils;


import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergii.stepanov on 08/05/2015.
 */
public abstract class CommonOperations {

    public static String parsTextByRegEx(String text, String regExpr) {
        String result = "";
        Pattern p = Pattern.compile(regExpr);
        Matcher m = p.matcher(text);
        while (m.find()) {
            result += m.group();
        }
        return result;
    }

    public String getCurrentDate(){
        DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
        Date date=new Date();
        return dateFormat.format(date);
    }


}
