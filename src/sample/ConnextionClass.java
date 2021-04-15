package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnextionClass {
//    public Connection connection;
//    public Connection getConnection(){
//        String dbname="rasadatabase";
//        String username="oussama";
//        String password="michiamoOUSSAMA";
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql:://localhost/"+dbname,username,password);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return connection;
    Connection conn = null;
    public Connection connectdb()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/rasadatabase","oussama","michiamoOUSSAMA");
            return conn;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    }
