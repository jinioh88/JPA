package com.sutdy.jpa.jpql;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeSalaryData {

    private Long id;
    private Double salary;
    private Double commissionPct;
}
