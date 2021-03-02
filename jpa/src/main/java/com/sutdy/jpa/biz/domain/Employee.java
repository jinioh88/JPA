package com.sutdy.jpa.biz.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "S_EMP")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String name;

}
