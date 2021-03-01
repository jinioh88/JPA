package com.sutdy.jpa.biz.client;

import com.sutdy.jpa.biz.domain.Department;
import com.sutdy.jpa.biz.domain.Employee;
import com.sutdy.jpa.biz.domain.EmployeeId;

import javax.persistence.*;
import java.util.List;

public class EmplyeeServiceClient {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Employee employee1 = em.find(Employee.class, 1L);
            employee1.setDept(null);
            Employee employee2 = em.find(Employee.class, 2L);
            employee1.setDept(null);

            Department department = em.find(Department.class, 1L);
            em.remove(department);

            tx.commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    private void dataSelect(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        Department department = em.find(Department.class, 1L);

        System.out.println("검색된 부서: " + department.getName());
        for (Employee employee : department.getEmployees()) {
            System.out.println(employee.getName());
        }
    }
}
