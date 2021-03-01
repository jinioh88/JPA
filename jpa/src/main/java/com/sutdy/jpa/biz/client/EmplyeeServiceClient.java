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

    private void dataDelete(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Department department = em.find(Department.class, 1L);

        // 직원의 부서 정보 수정
        List<Employee> employees = department.getEmployees();
        for (Employee employee : employees) {
            employee.standby();
        }

        // 부서 삭제
        em.remove(department);

        em.getTransaction().commit();
        em.close();
    }

    private void dataSelect(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        Department department = em.find(Department.class, 1L);

        System.out.println("검색된 부서: " + department.getName());
        for (Employee employee : department.getEmployees()) {
            System.out.println(employee.getName());
        }
    }

    private void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Department department = new Department();
        department.setName("개발부");

        Employee employee1 = new Employee();
        employee1.setName("둘리");
        employee1.setDept(department);

        Employee employee2 = new Employee();
        employee1.setName("또치");
        employee2.setDept(department);

        em.persist(department);

        System.out.println(department.getName() + "의 직원 수: " + department.getEmployees().size());

        em.getTransaction().commit();
        em.close();
    }
}
