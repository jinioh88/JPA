package com.sutdy.jpa.hibernate;

import java.sql.Timestamp;
import java.util.List;

public class EmployeeServiceClient {

    public static void main(String[] args) {
        EmployeeVO vo = new EmployeeVO();
        vo.setId(1L);
        vo.setName("오세진");
        vo.setStartDate(new Timestamp(System.currentTimeMillis()));
        vo.setTitle("과장");
        vo.setDeptName("총무부");
        vo.setSalary(5500.00);
        vo.setEmail("jinioh88@gmail.com");

        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.insertEmployee(vo);

        List<EmployeeVO> employeeVOList = employeeDAO.getEmployeeList();
        for (EmployeeVO employeeVO : employeeVOList) {
            System.out.println(" ===> " + employeeDAO.toString());
        }
    }
}
