package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String HOST = "mysql-ddata.alwaysdata.net";
    private static final String PORT = "3306";
    private static final String DB = "ddata_eshop";
    private static final String USER = "ddata";
    private static final String PASS = "Alwaysdata123!";
    
    

    public static Connection getConnection() {
        Connection cnx = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection(
                "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB + "?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&allowPublicKeyRetrieval=true",
                USER,
                PASS
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnx;
    }
}