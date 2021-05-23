package com.felype.creditcard.resource;

import com.felype.creditcard.contract.api.CreditCardsEndpoint;
import com.felype.creditcard.contract.model.CreditCard;
import com.felype.creditcard.contract.model.CreditCardsEnvelope;
import com.felype.creditcard.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CreditCardsResource implements CreditCardsEndpoint {

    private final CreditCardService creditCardService;

    @Override
    public Mono<CreditCard> postCreditCard(CreditCard creditCard) {
        if (creditCard.getId() > 0L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID");
        }

        return creditCardService.addCreditCard(creditCard);
    }

    @Override
    public Mono<CreditCardsEnvelope> getCreditCards() {
        return creditCardService.getCreditCards();
    }
}
