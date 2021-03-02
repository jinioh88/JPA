package com.sutdy.jpa;

import com.sutdy.jpa.biz.domain.Order;
import com.sutdy.jpa.biz.domain.Product;
import org.mockito.internal.matchers.Or;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class ManyToManyOneTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chap02");
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

        Order order = em.find(Order.class, 1L);

        List<Product> products = order.getProducts();
        for (Product product : products) {
            System.out.println("--->" + product.getName());
        }

        Product product = em.find(Product.class, 1L);
        List<Order> orders = product.getOrders();
        for (Order ord: orders) {
            System.out.println("---> " + ord.toString());
        }
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product1 = new Product();
        product1.setName("LG");
        em.persist(product1);

        Product product2 = new Product();
        product1.setName("삼성");
        em.persist(product2);

        Order order = new Order();
        order.setOrderDate(new Date());
        order.getProducts().add(product1);
        order.getProducts().add(product2);

        em.getTransaction().commit();
        em.close();
    }
}
