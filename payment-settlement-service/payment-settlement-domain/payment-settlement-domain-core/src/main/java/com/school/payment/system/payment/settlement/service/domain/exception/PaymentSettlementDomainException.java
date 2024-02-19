package com.school.payment.system.payment.settlement.service.domain.exception;

import com.school.payment.system.domain.exception.DomainException;

/**
 * Represents domain exceptions for payment settlement
 */
public class PaymentSettlementDomainException extends DomainException {
    public PaymentSettlementDomainException(String message) {
        super(message);
    }

    public PaymentSettlementDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
