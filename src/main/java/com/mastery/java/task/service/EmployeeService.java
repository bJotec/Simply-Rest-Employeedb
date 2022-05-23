package com.mastery.java.task.service;

import com.mastery.java.task.dao.IEmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    IEmployeeDao employeeDao;

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return this.employeeDao.getEmployeeById(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        this.employeeDao.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        this.employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        this.employeeDao.deleteEmployee(id);
    }
}
