package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

public class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao;


    @Test
    @Rollback(value = false)
    public void saveEmployeeTest() {

        Employee employee = new Employee();
        employee.setId(10);
        employee.setFirstName("Adam");
        employee.setLastName("Malinowski");
        employee.setDepartmentId(55);
        employee.setJobTitle("Testowy jobTitle");
        employee.setGender(Employee.Gender.MALE);

        employeeDao.addEmployee(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    public void getEmployeeTest() {

        Employee employee = employeeDao.getEmployeeById(1).get();
        Assertions.assertThat(employee.getId()).isEqualTo(1);

    }

    @Test
    public void getListOfEmployeesTest() {

        List<Employee> employees = employeeDao.getEmployeeList();

        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(value = false)
    public void updateEmployeeTest() {

        Employee employee = employeeDao.getEmployeeById(1).get();

        employee.setFirstName("ZMIANAIMIENIA");

        Employee employeeUpdated = employeeDao.addEmployee(employee);

        Assertions.assertThat(employeeUpdated.getFirstName()).isEqualTo("ZMIANAIMIENIA");

    }

    @Test
    @Rollback(value = false)
    public void deleteEmployeeTest() {

        Employee employee2 = null;

        employeeDao.deleteEmployee(3);

        Optional<Employee> employeeBox = employeeDao.getEmployeeById(3);

        if (employeeBox.isPresent()) {
            employee2 = employeeBox.get();
        }

        Assertions.assertThat(employee2).isNull();
    }
}
