package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDao implements IEmployeeDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Employee> query = session.createQuery("FROM com.mastery.java.task.dto.Employee WHERE id = :id");
        query.setParameter("id", id);
        try {
            Employee employee = query.getSingleResult();
            session.close();
            return Optional.of(employee);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(employee);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM com.mastery.java.task.dto.Employee WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Employee> getEmployeeList() {
        Session session = this.sessionFactory.openSession();
        Query<Employee> query = session.createQuery("FROM com.mastery.java.task.dto.Employee");
        List<Employee> employees = query.getResultList();
        session.close();
        return employees;
    }
}
