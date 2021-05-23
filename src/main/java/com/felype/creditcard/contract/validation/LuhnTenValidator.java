package com.felype.creditcard.contract.validation;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class LuhnTenValidator {

    public boolean isValid(String cardNumber) {
        boolean result = true;
        int sum = 0;

        if (StringUtils.isNotBlank(cardNumber)) {
            for (int i = cardNumber.length() - 1; i >= 0; i--) {
                char digit = cardNumber.charAt(i);

                if (digit < '0' || digit > '9') { //If digit is not a number, validation is finished. Card number is invalid.
                    result = false;
                    i = -1;
                } else {
                    int numericDigit = digit - '0';

                    if ((cardNumber.length() - i) % 2 == 0) {
                        numericDigit = numericDigit * 2;
                        sum = sum + numericDigit / 10;
                        sum = sum + numericDigit % 10;
                    } else {
                        sum = sum + numericDigit;
                    }
                }
            }
        } else {
            result = false;
        }

        return result && sum % 10 == 0;
    }

}
