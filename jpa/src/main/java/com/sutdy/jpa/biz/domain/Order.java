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

    @OneToMany(mappedBy = "order")
    private List<Item> items = new ArrayList<>();
}
