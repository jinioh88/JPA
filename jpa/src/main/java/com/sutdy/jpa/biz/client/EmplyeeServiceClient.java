package com.sutdy.jpa.biz.client;

import com.sutdy.jpa.biz.domain.Employee;
import com.sutdy.jpa.biz.domain.EmployeeId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmplyeeServiceClient {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            EmployeeId empId = new EmployeeId(1L, "guest123");

            Employee employee = em.find(Employee.class, empId);
            System.out.println("founded: " + employee.toString());

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
