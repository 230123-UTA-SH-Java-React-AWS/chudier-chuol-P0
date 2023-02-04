package com.revature.service;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.repository.authRepository;

public class authService {
   
    private final authRepository repo = new authRepository();
    private final ObjectMapper mapper = new ObjectMapper();
    

    public void login() {
        //collect and send input information to the authRepository
    }

    public void employeeRegister(String employeeJson) {
        

        try {
            Employee newEmployee = mapper.readValue(employeeJson, Employee.class);

            repo.saveEmployee(newEmployee);
            
        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}