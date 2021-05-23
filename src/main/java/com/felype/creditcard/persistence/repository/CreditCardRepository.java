package com.felype.creditcard.persistence.repository;

import com.felype.creditcard.persistence.entity.CreditCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardEntity, Long> {
}
