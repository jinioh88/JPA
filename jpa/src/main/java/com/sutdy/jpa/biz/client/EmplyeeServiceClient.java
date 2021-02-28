package com.sutdy.jpa.biz.client;

import com.sutdy.jpa.biz.domain.Employee;
import com.sutdy.jpa.biz.domain.EmployeeId;

import javax.persistence.*;

public class EmplyeeServiceClient {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");

        EntityManager em = emf.createEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);

        EntityTransaction tx = em.getTransaction();

        try {
            Employee employee = new Employee();
            employee.setName("둘리 수정함");
            employee.setId(1L);

            tx.begin();
            em.merge(employee);
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
