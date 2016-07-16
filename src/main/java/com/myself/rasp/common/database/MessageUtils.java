package com.myself.rasp.common.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by bruce.zhang on 7/15/16.
 */
public class MessageUtils {
    public static Properties properties;
//    public static MessageUtils instance;

    private MessageUtils() {

    }

//    public static MessageUtils getInstance() {
//        if (instance == null) {
//            instance = new MessageUtils();
//        }
//        if (properties == null) {
//            properties = new Properties();
//            loadData();
//        }
//        return instance;
//    }
    static {
        if (properties == null) {
            properties = new Properties();
            loadData();
        }
    }


    private static void loadData() {
        InputStream resourceAsStream = MessageUtils.class.getClassLoader().getResourceAsStream("properties.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(String key) {
        return properties.get(key).toString();
    }
}
