package com.sutdy.jpa.biz.domain;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "S_EMP")
public class Employee {

    @Id @GeneratedValue
    private Long id;
    private String name;
}
