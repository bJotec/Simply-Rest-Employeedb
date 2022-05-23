package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.Optional;

public interface IEmployeeDao {
    Optional<Employee> getEmployeeById(int id);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
}
