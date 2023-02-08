package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}