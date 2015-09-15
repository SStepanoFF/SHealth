package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.util.Properties;

public class Loader {
    static final String PROP_PATH =System.getProperty("user.dir")+"\\src\\test\\resources\\SutterHealth.properties";

    public static final String loadProperty(String name) {
        BufferedInputStream PropFileInStream=null;
        Properties Proper = new Properties();
        String value = "";
        try {
            PropFileInStream = new BufferedInputStream( new FileInputStream(PROP_PATH));
            Proper.load(PropFileInStream);
            if (name != null) {
                value = Proper.getProperty(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                PropFileInStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }


    public static final void updateProperty(String propName, String propValue){
        PropertiesConfiguration Config;
        try {
            Config =new PropertiesConfiguration(PROP_PATH);
            Config.setProperty(propName, propValue);
            Config.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
