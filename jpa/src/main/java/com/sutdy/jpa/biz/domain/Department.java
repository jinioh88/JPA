package com.sutdy.jpa.biz.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "employees")
@Entity
@Table(name = "S_DEPT")
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(length = 25, nullable = false)
    private String name;

    @OneToMany(mappedBy = "dept", cascade = {CascadeType.PERSIST})
    private List<Employee> employees = new ArrayList<>();
}
