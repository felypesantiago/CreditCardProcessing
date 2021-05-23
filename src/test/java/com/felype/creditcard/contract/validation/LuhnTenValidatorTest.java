package com.felype.creditcard.contract.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LuhnTenValidatorTest {

    @Test
    public void testIsValidReturnsTrue() {
        assertThat(LuhnTenValidator.isValid("1358954993914435")).isTrue();
    }

    @Test
    public void testIsValidReturnsFalse() {
        assertThat(LuhnTenValidator.isValid("1358954993914835")).isFalse();
    }

    @Test
    public void testIsValidHandlesNonNumbers() {
        assertThat(LuhnTenValidator.isValid("1358954aa3914835")).isFalse();
    }

    @Test
    public void testIsValidHandlesNull() {
        assertThat(LuhnTenValidator.isValid(null)).isFalse();
    }

    @Test
    public void testIsValidHandlesBlank() {
        assertThat(LuhnTenValidator.isValid(" ")).isFalse();
    }

    @Test
    public void testIsValidHandlesEmpty() {
        assertThat(LuhnTenValidator.isValid("")).isFalse();
    }

}
