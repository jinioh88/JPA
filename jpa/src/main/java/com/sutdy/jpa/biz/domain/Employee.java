package com.sutdy.jpa.biz.domain;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "S_EMP")
public class Employee {

    @Id
    private EmployeeId empId;
    private String name;
}
