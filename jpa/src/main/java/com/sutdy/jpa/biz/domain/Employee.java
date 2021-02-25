package com.sutdy.jpa.biz.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = {""})
@Setter @Getter
@Table(name = "S_EMP")
@Access(AccessType.FIELD)
public class Employee {

    @Id
    private Long id;

    private String name;
    private String mailId;

    @Column(name = "START_DATE")
    private Date startDate;

    private String title;

    @Column(name = "DEPT_NAME")
    private String deptName;

    private Double salary;

    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;
}
