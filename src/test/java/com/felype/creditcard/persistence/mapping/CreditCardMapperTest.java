package com.felype.creditcard.persistence.mapping;

import com.felype.creditcard.MockObjects;
import com.felype.creditcard.contract.model.CreditCard;
import com.felype.creditcard.persistence.entity.CreditCardEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreditCardMapperTest {

    @Test
    public void testToModel() {
        CreditCardEntity creditCardEntity = MockObjects.creditCardEntity(1);
        CreditCard creditCard = CreditCardMapper.toModel(creditCardEntity);

        assertThat(creditCard.getId()).isEqualTo(creditCardEntity.getId());
        assertThat(creditCard.getName()).isEqualTo(creditCardEntity.getName());
        assertThat(creditCard.getNumber()).isEqualTo(creditCardEntity.getNumber());
        assertThat(creditCard.getLimit()).isEqualTo(creditCardEntity.getLimit());
        assertThat(creditCard.getBalance()).isEqualTo(creditCardEntity.getBalance());
    }

    @Test
    public void testToEntity() {
        CreditCard creditCard = MockObjects.creditCard(1);
        CreditCardEntity creditCardEntity = CreditCardMapper.toEntity(creditCard);

        assertThat(creditCardEntity.getId()).isEqualTo(creditCard.getId());
        assertThat(creditCardEntity.getName()).isEqualTo(creditCard.getName());
        assertThat(creditCardEntity.getNumber()).isEqualTo(creditCard.getNumber());
        assertThat(creditCardEntity.getLimit()).isEqualTo(creditCard.getLimit());
        assertThat(creditCardEntity.getBalance()).isEqualTo(creditCard.getBalance());
    }
}
