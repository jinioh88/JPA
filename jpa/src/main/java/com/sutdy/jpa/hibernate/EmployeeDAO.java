package com.sutdy.jpa.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDAO {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public EmployeeDAO() {
        String config = "com/study/jpa/hibernate/hibernate.cfg.xml";
        sessionFactory = new Configuration().configure(config).buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.getTransaction();
    }

    public void insertEmployee(EmployeeVO vo) {
        try {
            transaction.begin();
            session.persist(vo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<EmployeeVO> getEmployeeList() {
        String jpql = "select e from EmployeeVO e order by e.id";
        List<EmployeeVO> employeeList = session.createQuery(jpql).getResultList();
        return employeeList;
    }
}
