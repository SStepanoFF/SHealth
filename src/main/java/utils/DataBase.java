package utils;


import java.sql.*;
import java.text.SimpleDateFormat;

public class DataBase {

    private static final String dbLogin=Loader.loadProperty("dbLogin");
    private static final String dbPass=Loader.loadProperty("dbPass");


    public static final String executeSQLQuery(String query, String columnName){
        String result = null;
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSets=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//Регистрируем драйвер
            conn = DriverManager.getConnection("jdbc:mysql://mysql-kstest2.t1.tenet:3306/sutter_health",
                    dbLogin, dbPass);//Установка соединения с БД
            statement = conn.createStatement();//Готовим запрос
            resultSets = statement.executeQuery(query);
            while (resultSets.next()) {
                if (query.toLowerCase().contains("date")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(Loader.loadProperty("dateFormat"));
                    Timestamp date = resultSets.getTimestamp(columnName);
                    result=dateFormat.format(date);
                }else result=resultSets.getString(columnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DB ERROR!");
        } finally {
            try {
                if(resultSets!=null) resultSets.close();
                if (statement!=null) statement.close();
                if (conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if (result==null){
            throw new RuntimeException("ERROR! Can't find in DB!");
        } else return result;
    }
}
