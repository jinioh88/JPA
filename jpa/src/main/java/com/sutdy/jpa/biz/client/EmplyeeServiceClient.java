package com.sutdy.jpa.biz.client;

import com.sutdy.jpa.biz.domain.Employee;

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
            Employee employee = new Employee();
            employee.setId(1L);
            employee.setName("둘리");

            tx.begin();

            em.persist(employee);
            tx.commit();

            Employee founded = em.find(Employee.class, 1L);
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
