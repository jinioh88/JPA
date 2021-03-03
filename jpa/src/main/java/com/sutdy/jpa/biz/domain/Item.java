package com.sutdy.jpa.biz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "S_ITEM")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORD_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Long price;
    private Long quantity;

    public void setOrder(Order order) {
        this.order = order;
        order.getItems().add(this);
    }
}
