package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employeeBox = this.employeeService.getEmployeeById(id);
        if (employeeBox.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(employeeBox.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Employee addEmployee(@RequestBody Employee employee) {
        this.employeeService.addEmployee(employee);
        return employee;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        this.employeeService.updateEmployee(employee);
        return employee;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable int id) {

        this.employeeService.deleteEmployee(id);
    }


}

