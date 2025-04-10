package com.example.springbootemployee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.springbootemployee.dao.EmployeeDAO;
import com.example.springbootemployee.model.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @PostMapping("/add")
    public String addEmployee(@RequestBody Employee employee) {
        employeeDAO.save(employee);
        return "Employee added: " + employee.getName();
    }
}
