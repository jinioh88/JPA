package com.sutdy.jpa.biz.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "employee")
@Entity
@Table(name = "S_EMP_CARD")
public class EmployeeCard {

    @Id
    @Column(name = "CARD_ID")
    private Long cardId;

    @Column(name = "EXPIRE_DATE")
    private Date expireDate;

    private String role;

    @MapsId
    @OneToOne
    @JoinColumn(name = "EMP_ID")
    private Employee employee;
}
