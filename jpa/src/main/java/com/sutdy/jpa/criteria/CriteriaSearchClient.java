package com.sutdy.jpa.criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaSearchClient {

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

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        com.sutdy.jpa.criteria.Department devDept = new com.sutdy.jpa.criteria.Department();
        devDept.setName("개발부");
        em.persist(devDept);

        com.sutdy.jpa.criteria.Department salseDept = new com.sutdy.jpa.criteria.Department();
        salseDept.setName("영업부");
        em.persist(salseDept);

        for (int i = 1; i <=3; i++) {
            Employee employee = new Employee();
            employee.setName("개발맨 "+ i);
            employee.setMailId("Corona " + i);
            employee.setDept(devDept);
        }

        em.getTransaction().commit();
        em.close();
    }

    private static void dataSelect(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

//        String searchCondition = "NAME";
//        String searchKeyword = "아르바이트";

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

        Root<com.sutdy.jpa.criteria.Employee> emp  = criteriaQuery.from(com.sutdy.jpa.criteria.Employee.class);

        Join<Employee, Department> dept = emp.join("dept");
        dept.fetch("employeeList");

        criteriaQuery.multiselect(emp.get("name"), emp.get("salary"), dept.get("name"));

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> resultList = query.getResultList();

        em.close();
    }
}
