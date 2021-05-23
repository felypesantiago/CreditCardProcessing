package com.felype.creditcard.service;

import com.felype.creditcard.MockObjects;
import com.felype.creditcard.persistence.entity.CreditCardEntity;
import com.felype.creditcard.persistence.mapping.CreditCardMapper;
import com.felype.creditcard.persistence.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CreditCardServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private CreditCardService creditCardService;

    @Test
    public void testListCreditCardsEmpty() {
        final List<CreditCardEntity> entities = Collections.emptyList();

        when(creditCardRepository.findAll()).thenReturn(entities);

        StepVerifier.create(creditCardService.getCreditCards()).assertNext(envelope -> {
            assertThat(envelope.getCreditCards()).isEmpty();
        }).verifyComplete();
    }

    @Test
    public void testListCreditCards() {
        final List<CreditCardEntity> entities = Arrays.asList(MockObjects.creditCardEntity(1), MockObjects.creditCardEntity(2));

        when(creditCardRepository.findAll()).thenReturn(entities);

        StepVerifier.create(creditCardService.getCreditCards()).assertNext(envelope -> {
            assertThat(envelope.getCreditCards()).size().isEqualTo(entities.size());
            assertThat(envelope.getCreditCards().get(0).getId()).isEqualTo(entities.get(0).getId());
            assertThat(envelope.getCreditCards().get(1).getId()).isEqualTo(entities.get(1).getId());
        }).verifyComplete();
    }

    @Test
    public void testAddCreditCard() {
        CreditCardEntity creditCardEntity = MockObjects.creditCardEntity(1);

        when(creditCardRepository.save(any())).thenReturn(creditCardEntity);

        StepVerifier.create(creditCardService.addCreditCard(CreditCardMapper.toModel(creditCardEntity)))
                .expectNext(CreditCardMapper.toModel(creditCardEntity))
                .verifyComplete();
    }

}
