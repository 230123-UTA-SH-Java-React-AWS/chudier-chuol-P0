package com.revature.repository;

import com.revature.model.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class employeeRepository {
    
    public void save(Employee employee) {

        ObjectMapper mapper = new ObjectMapper();
        
        String jsonObject = "";

        try {

            jsonObject = mapper.writeValueAsString(employee);

            File employeeFile = new File("./src/main/java/com/revature/repository/employee.json");
            employeeFile.createNewFile();

            FileWriter writer = new FileWriter("./src/main/java/com/revature/repository/employee.json");
            writer.write(jsonObject);
            writer.close();
            
        } catch(JsonGenerationException e) {
             e.printStackTrace();
        } catch(JsonMappingException e) {
            e.getStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}