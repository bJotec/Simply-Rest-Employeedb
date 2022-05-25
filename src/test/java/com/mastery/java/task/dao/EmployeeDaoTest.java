package com.mastery.java.task.dao;

import com.mastery.java.task.HibernateUtil;
import com.mastery.java.task.dto.Employee;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao;


    @Test
    @Rollback(value = false)
    public void saveEmployeeTest(){

        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .departmentId(55)
                .jobTitle("Testowy jobTitle")
                .build();

        employeeDao.addEmployee(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    public void getEmployeeTest(){

        Employee employee = employeeDao.getEmployeeById(1).get();

        Assertions.assertThat(employee.getId()).isEqualTo(1);

    }
}
