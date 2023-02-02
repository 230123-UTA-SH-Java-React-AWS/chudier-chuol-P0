package com.revature.service;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.repository.employeeRepository;

public class employeeService {
    

    public void login() {
        //
    }

    public void saveEmployee(String employeeJson) {
        employeeRepository repo = new employeeRepository();

        ObjectMapper mapper = new ObjectMapper();

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