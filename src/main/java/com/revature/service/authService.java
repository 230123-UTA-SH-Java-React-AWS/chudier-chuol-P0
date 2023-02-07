package com.revature.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.repository.authRepository;

public class authService {
   
    private final authRepository repo = new authRepository();
    private final ObjectMapper map = new ObjectMapper();

    public void employeeRegister(String employeeJson) {
        
        try {
            Employee newEmployee = map.readValue(employeeJson, Employee.class);

            repo.saveEmployee(newEmployee);
            
        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Employee loginEmployee(String currentUserJson) {
        //collects and sends user input to the login repo
        Employee foundUser = null;
        try {
            Employee currentUser = map.readValue(currentUserJson, Employee.class);

            foundUser = repo.loginEmployee(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundUser;
    }

    public String getAllEmployee() {

        List<Employee> listOfEmp = repo.getAllEmployee();
    
        String jsonString = "";

        try {
            jsonString = map.writeValueAsString(listOfEmp);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return jsonString;
    }

}