package com.felype.creditcard.contract.api;

import com.felype.creditcard.contract.model.CreditCard;
import com.felype.creditcard.contract.model.CreditCardsEnvelope;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api
@RequestMapping(value = "/credit-cards", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CreditCardsEndpoint {

    @ApiOperation(value = "Adds a credit card to the service.",
            notes = "Allows users to add credit cards.",
            tags = "Credit Cards")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Credit card successfully added.", response = CreditCard.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Request is invalid."),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error."),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAVAILABLE, message = "Service temporarily unavailable due to service maintenance, heavy load or other issues.")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<CreditCard> postCreditCard(@ApiParam(value = "Credit card.", required = true)
                                    @Valid @RequestBody CreditCard creditCard);

    @ApiOperation(value = "Gets all credit cards.",
            notes = "Allows user to retrieve credit cards.",
            tags = "Credit Cards")
    @ApiResponses({
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Credit cards successfully retrieved.", response = CreditCardsEnvelope.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Request is invalid."),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error."),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAVAILABLE, message = "Service temporarily unavailable due to service maintenance, heavy load or other issues.")})
    @GetMapping
    Mono<CreditCardsEnvelope> getCreditCards();

}
