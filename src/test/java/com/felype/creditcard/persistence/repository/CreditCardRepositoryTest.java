package com.felype.creditcard.persistence.repository;

import com.felype.creditcard.MockObjects;
import com.felype.creditcard.persistence.entity.CreditCardEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CreditCardRepositoryTest {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Test
    public void testAddCreditCard() {
        CreditCardEntity creditCardEntity = MockObjects.creditCardEntity(0);

        CreditCardEntity savedCreditCard = creditCardRepository.save(creditCardEntity);

        assertThat(savedCreditCard).isNotNull();
        assertThat(savedCreditCard.getId()).isGreaterThan(0);
        assertThat(savedCreditCard.getName()).isEqualTo(creditCardEntity.getName());
        assertThat(savedCreditCard.getNumber()).isEqualTo(creditCardEntity.getNumber());
        assertThat(savedCreditCard.getLimit()).isEqualTo(creditCardEntity.getLimit());
    }

    @Test
    public void testListCreditCards() {
        creditCardRepository.save(MockObjects.creditCardEntity(0));

        Iterable<CreditCardEntity> all = creditCardRepository.findAll();

        assertThat(all.spliterator().getExactSizeIfKnown()).isEqualTo(1);
    }

}
