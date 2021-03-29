package com.sutdy.jpa.criteria;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString(exclude = "dept")
@Entity
@Table(name = "S_EMP")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "MAIL_ID")
    private String mailId;

    @Column(name = "START_DATE")
    private Date startDate;

    private String title;

    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department dept;

    private Double salary;

    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;

    public void setDept(Department department) {
        this.dept = department;
        department.getEmployeeList().add(this);
    }
}
