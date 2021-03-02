package com.sutdy.jpa.biz.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString(exclude = "productList")
@Entity
@Table(name = "S_ORD")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    private Double total;

    @ManyToMany
    @JoinTable(name = "S_ITEM", joinColumns = @JoinColumn(name = "ORD_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"ORD_ID", "PRODUCT_ID"}))
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.getOrders().add(this);
    }
}
