package com.school.payment.system.payment.settlement.service.domain.exception;

/**
 * Represents exception for not finding parent
 */
public class ParentNotFoundException extends PaymentSettlementDomainException{
    public ParentNotFoundException(String message) {
        super(message);
    }

    public ParentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
