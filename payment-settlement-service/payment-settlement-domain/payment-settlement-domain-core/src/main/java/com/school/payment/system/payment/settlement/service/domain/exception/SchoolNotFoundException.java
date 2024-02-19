package com.school.payment.system.payment.settlement.service.domain.exception;

/**
 * Represents exception for not finding a school
 */
public class SchoolNotFoundException extends PaymentSettlementDomainException{
    public SchoolNotFoundException(String message) {
        super(message);
    }

    public SchoolNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
