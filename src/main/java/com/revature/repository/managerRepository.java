package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.model.Manager;
import com.revature.utils.connectionUtil;

public class managerRepository {
    
    public void saveManager(Manager manager) {

        String sql = "insert into manager (email, password) values (?, ?)";

        try(Connection con = connectionUtil.getConnection()) {
            
            PreparedStatement prepstmt = con.prepareStatement(sql);

            prepstmt.setString(1, manager.getEmail());
            prepstmt.setString(2, manager.getPassword());
            prepstmt.execute();

        } catch (Exception e) {
            
            e.printStackTrace();
            System.out.println("Exception was thrown in the repository package agent Chudier");
        }
    }

    public Manager loginManager(Manager employee) {
        //retrieve user data from the database using the email and compare passwords
        String sql = "select * from manager where email = ?";

        Manager currentManager = null;
        
        boolean authenticated;

        try (Connection con = connectionUtil.getConnection()) {
            
            PreparedStatement prepstmt = con.prepareStatement(sql);
            
            prepstmt.setString(1, employee.getEmail());
            
            ResultSet rs = prepstmt.executeQuery();

            while (rs.next()) {
                authenticated = employee.getPassword().equals(rs.getString("password"));
                
                currentManager = new Manager();

                if(authenticated) {
                    currentManager.setEmail(rs.getString("email"));
                    currentManager.setPassword(rs.getString("password"));
                } else if(authenticated != true) {
                    currentManager = null;
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentManager;
    }
}