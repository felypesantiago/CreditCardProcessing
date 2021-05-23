package com.felype.creditcard.resource;

import com.felype.creditcard.MockObjects;
import com.felype.creditcard.contract.model.CreditCard;
import com.felype.creditcard.contract.model.CreditCardsEnvelope;
import com.felype.creditcard.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CreditCardsResourceTest {

    @Mock
    private CreditCardService creditCardService;

    @InjectMocks
    private CreditCardsResource creditCardsResource;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient.bindToController(creditCardsResource)
                .configureClient()
                .build();
    }

    @Test
    public void testPostCreditCard() {
        final CreditCard creditCard = MockObjects.creditCard(0);
        final CreditCard expectedCreditCard = MockObjects.creditCard(1);

        when(creditCardService.addCreditCard(any(CreditCard.class))).thenReturn(Mono.just(expectedCreditCard));

        final CreditCard receivedCreditCard = webTestClient.post().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creditCard)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CreditCard.class)
                .returnResult().getResponseBody();

        assertEquals(expectedCreditCard, receivedCreditCard);
    }

    @Test
    public void testPostCreditCardInvalidId() {
        final CreditCard creditCard = MockObjects.creditCard(1);

        webTestClient.post().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creditCard)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testPostCreditCardInvalidName() {
        final CreditCard creditCard = MockObjects.creditCard(0, "", "1358954993914435",
                BigDecimal.ONE, BigDecimal.ZERO);

        webTestClient.post().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creditCard)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testPostCreditCardInvalidNumberTooLong() {
        final CreditCard creditCard = MockObjects.creditCard(0, "name",
                "11111222223333344444", BigDecimal.ONE, BigDecimal.ZERO);

        webTestClient.post().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creditCard)
                .exchange()
                .expectStatus().isBadRequest();
    }


    @Test
    public void testPostCreditCardInvalidNumberNull() {
        final CreditCard creditCard = MockObjects.creditCard(0, "name", null,
                BigDecimal.ONE, BigDecimal.ZERO);

        webTestClient.post().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creditCard)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testPostCreditCardInvalidNumberNonLuhnTen() {
        final CreditCard creditCard = MockObjects.creditCard(0, "name", "13589549939144351111",
                BigDecimal.ONE, BigDecimal.ZERO);

        webTestClient.post().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creditCard)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testPostCreditCardNullLimit() {
        final CreditCard creditCard = MockObjects.creditCard(0, "name", "1358954993914435",
                null, BigDecimal.ZERO);

        webTestClient.post().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creditCard)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testPostCreditCardInvalidLimit() {
        final CreditCard creditCard = MockObjects.creditCard(0, "name", "1358954993914435",
                BigDecimal.valueOf(-100), BigDecimal.ZERO);

        webTestClient.post().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creditCard)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testPostCreditCardInternalError() {
        when(creditCardService.addCreditCard(any(CreditCard.class))).thenReturn(Mono.error(new RuntimeException()));

        webTestClient.post().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(MockObjects.creditCard(0))
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    public void testGetCreditCards() {
        final CreditCardsEnvelope expectedCreditCardsEnvelope = CreditCardsEnvelope.builder()
                .creditCard(MockObjects.creditCard(1))
                .creditCard(MockObjects.creditCard(2)).build();

        when(creditCardService.getCreditCards()).thenReturn(Mono.just(expectedCreditCardsEnvelope));

        final CreditCardsEnvelope receivedCreditCardsEnvelope = webTestClient.get().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CreditCardsEnvelope.class)
                .returnResult().getResponseBody();

        assertEquals(expectedCreditCardsEnvelope, receivedCreditCardsEnvelope);
    }

    @Test
    public void testGetCreditCardsEmpty() {
        final CreditCardsEnvelope expectedCreditCardsEnvelope = CreditCardsEnvelope.builder().build();

        when(creditCardService.getCreditCards()).thenReturn(Mono.just(expectedCreditCardsEnvelope));

        final CreditCardsEnvelope receivedCreditCardsEnvelope = webTestClient.get().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CreditCardsEnvelope.class)
                .returnResult().getResponseBody();

        assertEquals(expectedCreditCardsEnvelope, receivedCreditCardsEnvelope);
    }

    @Test
    public void testGetCreditCardsInternalError() {
        when(creditCardService.getCreditCards()).thenReturn(Mono.error(new RuntimeException()));

        webTestClient.get().uri("/credit-cards")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
