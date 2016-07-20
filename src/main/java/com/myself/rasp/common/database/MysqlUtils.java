package com.myself.rasp.common.database;

import com.myself.rasp.common.vo.Hlogs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruce.zhang on 7/15/16.
 */
public class MysqlUtils {
    private static MysqlUtils instance;
    private static String URL = "mysql.url";
    private static String USER_NAME = "mysql.username";
    private static String PASSWORD = "mysql.password";
    private Connection connection;

    private MysqlUtils() {
    }

    public static MysqlUtils getInstance() {
        if (instance == null) {
            instance = new MysqlUtils();
        }
        return instance;
    }

    private void openConnection() {
        try {
            String url = MessageUtils.getMessage(URL);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, MessageUtils.getMessage(USER_NAME), MessageUtils.getMessage(PASSWORD));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Hlogs> querySQL(String sql) {
        openConnection();
        List<Hlogs> result = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Hlogs hlogs = new Hlogs();
                String log = resultSet.getString("log");
                int id = resultSet.getInt("id");
                hlogs.setLog(log);
                hlogs.setId(id);
                hlogs.setCreateTime(resultSet.getTimestamp("createTime"));
                result.add(hlogs);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List queryNormalSQL(String sql) {
        openConnection();
        List result = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
//                String log = resultSet.getString(0);
//                int id = resultSet.getInt("id");



//                result.add(hlogs);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
