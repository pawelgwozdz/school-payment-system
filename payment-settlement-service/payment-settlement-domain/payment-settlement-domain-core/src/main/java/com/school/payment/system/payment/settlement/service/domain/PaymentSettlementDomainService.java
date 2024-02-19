package com.school.payment.system.payment.settlement.service.domain;

import com.school.payment.system.payment.settlement.service.domain.entity.AttendanceTime;
import com.school.payment.system.payment.settlement.service.domain.entity.PaymentSettlementCapable;

/**
 * interface for payment settlement domain service
 */
public interface PaymentSettlementDomainService {

    void calculateMinAndMaxDate(AttendanceTime attendanceTime);

    void validateAndCalculatePaymentSettlement(PaymentSettlementCapable paymentSettlementCapable);
}
