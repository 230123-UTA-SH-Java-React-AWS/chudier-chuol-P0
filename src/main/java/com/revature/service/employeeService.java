package com.revature.service;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.repository.employeeRepository;

public class employeeService {
   
    private final employeeRepository repo = new employeeRepository();
    private final ObjectMapper mapper = new ObjectMapper();
    

    public void login() {
        //
    }

    public void saveEmployee(String employeeJson) {
        

        try {
            Employee newEmployee = mapper.readValue(employeeJson, Employee.class);

            repo.save(newEmployee);
            
        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}