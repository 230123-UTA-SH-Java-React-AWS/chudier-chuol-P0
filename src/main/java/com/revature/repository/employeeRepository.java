package com.revature.repository;

import com.revature.model.Employee;
import com.revature.utils.connectionUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class employeeRepository {
    
    public void save(Employee employee) {

        // ObjectMapper mapper = new ObjectMapper();
        
        // String jsonObject = "";

        // try {

        //     jsonObject = mapper.writeValueAsString(employee);

        //     File employeeFile = new File("./src/main/java/com/revature/repository/employee.json");
        //     employeeFile.createNewFile();

        //     FileWriter writer = new FileWriter("./src/main/java/com/revature/repository/employee.json");
        //     writer.write(jsonObject);
        //     writer.close();
            
        // } catch(JsonGenerationException e) {
        //      e.printStackTrace();
        // } catch(JsonMappingException e) {
        //     e.getStackTrace();
        // } catch(IOException e) {
        //     e.printStackTrace();
        // }
        
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