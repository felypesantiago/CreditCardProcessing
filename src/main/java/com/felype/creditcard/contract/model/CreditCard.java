package com.felype.creditcard.contract.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.felype.creditcard.contract.validation.CreditCardNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@ApiModel(description = "Represents a credit card.")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    @ApiModelProperty(value = "Unique credit card ID.", example = "1")
    private long id;

    @ApiModelProperty(required = true, value = "Credit card name.", example = "Main card")
    @NotBlank
    private String name;

    @ApiModelProperty(required = true, value = "Credit card number.", example = "1358954993914435")
    @NotBlank
    @Length(max = 19)
    @CreditCardNumber
    private String number;

    @ApiModelProperty(required = true, value = "Credit card limit.", example = "10000.00")
    @PositiveOrZero
    @NotNull
    private BigDecimal limit;

    @ApiModelProperty(value = "Credit card balance. New cards start with a £0 balance, " +
            "unless specified in request.", example = "5000.00")
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

}
