package com.felype.creditcard.persistence.mapping;

import com.felype.creditcard.contract.model.CreditCard;
import com.felype.creditcard.persistence.entity.CreditCardEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CreditCardMapper {

    public CreditCard toModel(CreditCardEntity entity) {
        return CreditCard.builder()
                .id(entity.getId())
                .name(entity.getName())
                .number(entity.getNumber())
                .limit(entity.getLimit())
                .balance(entity.getBalance()).build();
    }

    public CreditCardEntity toEntity(CreditCard model) {
        return CreditCardEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .number(model.getNumber())
                .limit(model.getLimit())
                .balance(model.getBalance()).build();
    }

}
