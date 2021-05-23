package com.felype.creditcard.contract.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@ApiModel(description = "Represents a list of credit cards.")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardsEnvelope {

    @ApiModelProperty(value = "List of credit cards")
    @Singular
    private List<CreditCard> creditCards;

}
