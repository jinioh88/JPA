package com.sutdy.jpa;

import com.sutdy.jpa.biz.domain.Item;
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

        List<Item> items = order.getItems();
        for (Item item : items) {
            System.out.println(item.getProduct().getName());
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
        em.persist(order);

        Item item1 = new Item();
        item1.setOrder(order);
        item1.setProduct(product1);
        item1.setPrice(100000L);
        item1.setQuantity(2L);
        em.persist(item1);

        Item item2 = new Item();
        item2.setOrder(order);
        item2.setProduct(product2);
        item2.setPrice(120000L);
        item2.setQuantity(3L);
        em.persist(item2);

        em.getTransaction().commit();
        em.close();
    }
}
