package com.revature.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.repository.authRepository;
import com.revature.repository.ticketRepository;

public class authService {
   
    private final authRepository repo = new authRepository();
    private final ObjectMapper mapper = new ObjectMapper();

    public String employeeRegister(String employeeJson) {
        String registered = "";
        
        try {
            Employee newEmployee = mapper.readValue(employeeJson, Employee.class);

            registered = repo.saveEmployee(newEmployee);
            
        } catch(JsonParseException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return registered;
    }

    public boolean loginEmployee(String currentUserJson) {
        //collects and sends user input to the login repo
        boolean foundUser = false;
        try {
            Employee currentUser = mapper.readValue(currentUserJson, Employee.class);

            foundUser = repo.loginEmployee(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundUser;
    }

    // public String getAllEmployee() {

    //     HashSet<String> listOfEmp = repo.getAllEmployee();

    //     try {
    //         listOfEmp = mapper.writeValueAsString(listOfEmp);
    //     } catch (JsonGenerationException e) {
    //         e.printStackTrace();
    //     } catch (JsonMappingException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
 
    //     return listOfEmp;
    // }

}