package com.sutdy.jpa;

import com.sutdy.jpa.jpql.Employee;
import com.sutdy.jpa.jpql.EmployeeSalaryData;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class JPQLBasicClient {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");
        try {
            dataInsert(emf);
            dataSelect(emf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
        }
    }

    private static void dataSelect(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        // JPQL
        String jpql = "select e, e.dept from Employee e order by e.id;
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        int pageNumber = 2;
        int pageSize = 5;
        int startNum = (pageNumber * pageSize) - pageSize;
        query.setFirstResult(startNum);
        query.setMaxResults(pageSize);

        List<Object[]> resultList = query.getResultList();


        em.close();
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setName("직원 " + i);
            employee.setDeptName("개발부");
            em.persist(employee);
        }

        em.getTransaction().commit();
        em.close();
    }
}

