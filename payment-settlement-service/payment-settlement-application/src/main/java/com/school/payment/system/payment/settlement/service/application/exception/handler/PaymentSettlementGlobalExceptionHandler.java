package com.school.payment.system.payment.settlement.service.application.exception.handler;

import com.school.payment.system.application.handler.ErrorDTO;
import com.school.payment.system.payment.settlement.service.domain.exception.ParentNotFoundException;
import com.school.payment.system.payment.settlement.service.domain.exception.PaymentSettlementDomainException;
import com.school.payment.system.payment.settlement.service.domain.exception.SchoolNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * handles exceptions for payment settlement service
 */
@Slf4j
@RestControllerAdvice
public class PaymentSettlementGlobalExceptionHandler {

    @ExceptionHandler(value = {PaymentSettlementDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(PaymentSettlementDomainException paymentSettlementDomainException) {
        log.error(paymentSettlementDomainException.getMessage(), paymentSettlementDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(paymentSettlementDomainException.getMessage())
                .build();
    }

    @ExceptionHandler(value = {ParentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(ParentNotFoundException parentNotFoundException) {
        log.error(parentNotFoundException.getMessage(), parentNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(parentNotFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(value = {SchoolNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(SchoolNotFoundException schoolNotFoundException) {
        log.error(schoolNotFoundException.getMessage(), schoolNotFoundException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(schoolNotFoundException.getMessage())
                .build();
    }
}
