package com.sutdy.jpa;

import com.sutdy.jpa.biz.domain.Employee;
import com.sutdy.jpa.biz.domain.EmployeeCard;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class OneToOneClient {

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

        EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
        System.out.println(employeeCard.getCardId());
        System.out.println(employeeCard.getEmployee().getName());

        Employee employee = em.find(Employee.class, 1L);
        System.out.println(employee.getName());
        System.out.println(employee.getCard().getExpireDate());
    }

    private static void dataInsert(EntityManagerFactory emf) throws ParseException {
        EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();

        Employee employee = new Employee();
        employee.setName("둘리");
        em.persist(employee);

        EmployeeCard card = new EmployeeCard();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        card.setExpireDate(dateFormat.parse("2025-12-31"));
        card.setRole("MASTER");
        card.setEmployee(employee);

        em.persist(card);
        em.getTransaction().commit();
        em.close();
    }
}
