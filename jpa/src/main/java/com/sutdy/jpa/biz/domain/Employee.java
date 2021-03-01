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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID")
    private Department dept;

    public void setDept(Department department) {
        this.dept = department;
        // Department 엔티티의 컬렉션에도 Employee 참조 하도록
        department.getEmployees().add(this);
    }
}
