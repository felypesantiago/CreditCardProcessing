package com.felype.creditcard.service;

import com.felype.creditcard.contract.model.CreditCard;
import com.felype.creditcard.contract.model.CreditCardsEnvelope;
import com.felype.creditcard.persistence.mapping.CreditCardMapper;
import com.felype.creditcard.persistence.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository repository;

    public Mono<CreditCard> addCreditCard(CreditCard creditCard) {
        return Mono.fromCallable(() -> repository.save(CreditCardMapper.toEntity(creditCard)))
                .subscribeOn(Schedulers.boundedElastic())
                .map(CreditCardMapper::toModel);
    }

    public Mono<CreditCardsEnvelope> getCreditCards() {
        return Mono.fromCallable(repository::findAll)
                .subscribeOn(Schedulers.boundedElastic())
                .map(iterable -> {
                    List<CreditCard> list = new ArrayList<>();

                    iterable.forEach(entity -> list.add(CreditCardMapper.toModel(entity)));

                    return CreditCardsEnvelope.builder()
                            .creditCards(list).build();
                });
    }

}
