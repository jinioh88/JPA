package com.sutdy.jpa.jpql;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "S_DEPT")
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
    private Long deptId;

    private String name;

    @OrderColumn(name = "EMP_IDX")
    @OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
    private List<Employee> employeeList = new ArrayList<>();
}
