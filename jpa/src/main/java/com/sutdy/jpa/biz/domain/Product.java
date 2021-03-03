package com.sutdy.jpa.biz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "S_PRODUCT")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "SHORT_DESC")
    private String shortDesc;

    private String category;
}
