package com.revature.repository;

import com.revature.model.Employee;
import com.revature.utils.connectionUtil;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class authRepository {
    
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
    //do i need to convert the java object back into a string?
    public Employee loginEmployee(Employee employee) {
        //retrieve user data from the database using the email and compare passwords
        String sql = "select * from employee where email = ?";

        Employee currentEmployee = null;
        
        boolean authenticated;

        try (Connection con = connectionUtil.getConnection()) {
            
            PreparedStatement prepstmt = con.prepareStatement(sql);
            
            prepstmt.setString(1, employee.getEmail());
            
            ResultSet rs = prepstmt.executeQuery();

            while (rs.next()) {
                authenticated = employee.getPassword().equals(rs.getString("password"));
                
                currentEmployee = new Employee();

                if(authenticated) {
                    currentEmployee.setEmployeeid(rs.getInt("employeeid"));
                    currentEmployee.setEmail(rs.getString("email"));
                    currentEmployee.setPassword(rs.getString("password"));
                }
                
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentEmployee;
        
    }

    public List<Employee> getAllEmployee() {
        String sql = "select * from employee ";

        List<Employee> listOfEmployee = new ArrayList<>();

        try (Connection con = connectionUtil.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employee newEmployee = new Employee();

                newEmployee.setEmployeeid(rs.getInt("employeeid"));
                newEmployee.setEmail(rs.getString("email"));
                newEmployee.setPassword(rs.getString("password"));

                listOfEmployee.add(newEmployee);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfEmployee;
    }
}