package com.felype.creditcard.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "creditcards")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String name;

    private String number;

    @Column(name = "limit_amount")
    private BigDecimal limit;

    private BigDecimal balance;

}
