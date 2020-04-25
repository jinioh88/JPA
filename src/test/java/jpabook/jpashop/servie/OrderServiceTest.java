package jpabook.jpashop.servie;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() {
        // given
        Member member = createMember();

        Book book = createBook("JPA", 15000, 10);

        int orderCount = 1;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("상품 주문시 종류 수", 1, getOrder.getOrderItems().size());
        assertEquals("상품 주문가격", 15000 * orderCount, getOrder.getTotalPrice());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 주문_재고수량초과() {
        // given
        Member member = createMember();

        Book item = createBook("JPA", 15000, 10);

        int orderCount = 11;

        // when
        orderService.order(member.getId(), item.getId(), orderCount);

        // then
        fail("재고 수량 부족 예외 발생해야...");
    }

    @Test
    public void 주문취소() {
        // given
        Member member = createMember();
        Book item = createBook("JPA", 15000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order getORder = orderRepository.findOne(orderId);

        assertEquals("주문 취소는 상태 CANCEL", OrderStatus.CANCEL, getORder.getStatus());
        assertEquals("주문 취소 재고 증가", 10, item.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() {

    }

    private Member createMember() {
        Member member = new Member();
        member.setName("오세진");
        member.setAddress(new Address("서울", "강가", "113-111"));
        em.persist(member);

        return member;
    }

    private Book createBook(String name, int price, int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);

        return book;
    }
}
