package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionUtil {
    
    private static Connection con;

    private connectionUtil() {
        con = null; 
    }

    public static Connection getConnection() {
        try {
            if (con != null && !con.isClosed()) {
                return con;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception was thrown in the util package agent Chudier");
        } 
        
        String url, user, pass;
       
        url = System.getenv("url");
        user = System.getenv("user");
        pass = System.getenv("pass");
        
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Agent Chudier, you may have provided the wrong creditentials");
        }

        return con;
    }

}