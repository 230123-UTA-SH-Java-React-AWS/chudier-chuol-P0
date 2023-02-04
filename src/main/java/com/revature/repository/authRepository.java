package com.revature.repository;

import com.revature.model.Employee;
import com.revature.utils.connectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class authRepository {
    
    public void authenticate() {
        //retrieve user data from the database using the email and compare passwords 
    }
    
    public void saveEmployee(Employee employee) {

            String sql = "insert into employee (email, password) values (?, ?) ";

            try(Connection con = connectionUtil.getConnection()) {
                
                PreparedStatement prepstmt = con.prepareStatement(sql);

                prepstmt.setString(1, employee.getEmail());
                prepstmt.setString(2, employee.getPassword());

                prepstmt.execute();

            } catch (Exception e) {
              
                e.printStackTrace();
                System.out.println("Exception was thrown in the repository package agent Chudier");
            }
            
    }
}