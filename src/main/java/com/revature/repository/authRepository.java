package com.revature.repository;

import com.revature.model.Employee;
import com.revature.utils.connectionUtil;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class authRepository {
    
    public String saveEmployee(Employee employee) {

        if (!loginEmployee(employee)) {
            
            String sql = "insert into employees (email, password) values (?, ?) ";

            try(Connection con = connectionUtil.getConnection()) {
                
                PreparedStatement prepstmt = con.prepareStatement(sql);

                prepstmt.setString(1, employee.getEmail());
                prepstmt.setString(2, employee.getPassword());
                prepstmt.execute();

            } catch (Exception e) {
                
                e.printStackTrace();
                System.out.println("Exception was thrown in the repository package agent Chudier");
            }
        } else {
            return "An account is already registered with your email address";
        }
        
        return "You have successfully registered an account";
    }
    
    public boolean loginEmployee(Employee employee) {
        //retrieve user data from the database using the email and compare passwords
        boolean authenticated = false;

        String sql = "select * from employees where email = ?";

        try (Connection con = connectionUtil.getConnection()) {
            
            PreparedStatement prepstmt = con.prepareStatement(sql);
            
            prepstmt.setString(1, employee.getEmail());
            
            ResultSet rs = prepstmt.executeQuery();

            while (rs.next()) {

                if(employee.getPassword().equals(rs.getString("password"))) {
                    authenticated = true;
                } 
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticated;
    }

    public boolean checkManagerStatus(int id){
        
        String sql = "select role from employees where id = ?";
        
        try(Connection con = connectionUtil.getConnection()) {
            
            PreparedStatement prepstmt = con.prepareStatement(sql);
           
            prepstmt.setInt(1, id);
            
            ResultSet rs = prepstmt.executeQuery();
            
            rs.next();
            
            String employeeRole = rs.getString(1);
            
            if(employeeRole.equals("MANAGER")){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public HashSet<String> getAllEmployee() {
    
        HashSet<String> listOfEmployee = new HashSet<>();

        String sql = "select * from employees ";

        try (Connection con = connectionUtil.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
            
                listOfEmployee.add(rs.getString(2));
                listOfEmployee.add(rs.getString(4));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfEmployee;
    }
}