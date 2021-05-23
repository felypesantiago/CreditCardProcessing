package com.felype.creditcard;

import com.felype.creditcard.contract.model.CreditCard;
import com.felype.creditcard.persistence.entity.CreditCardEntity;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class MockObjects {

    public CreditCard creditCard(int id, String name, String number, BigDecimal limit, BigDecimal balance) {
        return CreditCard.builder()
                .id(id)
                .name(name)
                .number(number)
                .limit(limit)
                .balance(balance).build();
    }

    public CreditCard creditCard(int id) {
        return creditCard(id, "name", "1358954993914435", new BigDecimal("100.00"), BigDecimal.ZERO);
    }

    public CreditCardEntity creditCardEntity(int id, String name, String number, BigDecimal limit, BigDecimal balance) {
        return CreditCardEntity.builder()
                .id(id)
                .name(name)
                .number(number)
                .limit(limit)
                .balance(balance).build();
    }

    public CreditCardEntity creditCardEntity(int id) {
        return creditCardEntity(id, "name", "1358954993914435", new BigDecimal("100.00"), BigDecimal.ZERO);
    }

}
