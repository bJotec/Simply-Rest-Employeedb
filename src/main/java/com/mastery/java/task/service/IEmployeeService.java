package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.Optional;

public interface IEmployeeService {
    Optional<Employee> getEmployeeById(int id);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
}
