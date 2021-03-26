package com.sutdy.jpa.criteria;

import com.sutdy.jpa.biz.domain.Department;
import com.sutdy.jpa.biz.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

        Department department = new Department();
        department.setName("개발부");

        com.sutdy.jpa.biz.domain.Employee employee1 = new com.sutdy.jpa.biz.domain.Employee();
        employee1.setName("둘리");
//        employee1.setDept(department);

        com.sutdy.jpa.biz.domain.Employee employee2 = new Employee();
        employee1.setName("또치");
//        employee2.setDept(department);

        em.persist(department);

        System.out.println(department.getName() + "의 직원 수: " + department.getEmployees().size());

        em.getTransaction().commit();
        em.close();
    }

    private static void dataSelect(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        String searchCondition = "NAME";
        String searchKeyword = "아르바이트";

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<com.sutdy.jpa.criteria.Employee> criteriaQuery =
                builder.createQuery(com.sutdy.jpa.criteria.Employee.class);

        Root<com.sutdy.jpa.criteria.Employee> emp  = criteriaQuery.from(com.sutdy.jpa.criteria.Employee.class);

        criteriaQuery.select(emp);

        if (searchCondition.equals("NAME")) {
            criteriaQuery.where(builder.equal(emp.get("name"), searchKeyword));
        } else if (searchCondition.equals("MAILID")) {
            criteriaQuery.where(builder.equal(emp.get("mailId"), searchKeyword));
        } else if (searchCondition.equals("TITLE")) {
            criteriaQuery.where(builder.equal(emp.get("title"), searchKeyword));
        }

        TypedQuery<com.sutdy.jpa.criteria.Employee> query = em.createQuery(criteriaQuery);
        List<com.sutdy.jpa.criteria.Employee> resultList = query.getResultList();
        for (com.sutdy.jpa.criteria.Employee result : resultList) {
            System.out.println(result.toString());
        }

        em.close();
    }
}
