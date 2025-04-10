package com.example.springbootemployee.dao;

import com.example.springbootemployee.dao.EmployeeDAO;
import com.example.springbootemployee.model.Employee;

public interface EmployeeDAO {
    void save(Employee employee);
}
