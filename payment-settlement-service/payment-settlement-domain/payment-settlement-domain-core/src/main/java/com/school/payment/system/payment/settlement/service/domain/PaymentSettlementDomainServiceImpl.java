package com.school.payment.system.payment.settlement.service.domain;

import com.school.payment.system.payment.settlement.service.domain.entity.AttendanceTime;
import com.school.payment.system.payment.settlement.service.domain.entity.PaymentSettlementCapable;
import lombok.extern.slf4j.Slf4j;

/**
 * implementation of interface for payment settlement domain service
 */
@Slf4j
public class PaymentSettlementDomainServiceImpl implements PaymentSettlementDomainService {

    /**
     * calculates min and max date for attendance time
     * @param attendanceTime attendance time with year and month
     */
    @Override
    public void calculateMinAndMaxDate(AttendanceTime attendanceTime) {
        attendanceTime.calculateMinAndMaxDate();
        log.info("Min and Max attendance time for year: {} and month: {} calculated", attendanceTime.getYear(), attendanceTime.getMonth());
    }

    /**
     * validates classes and calculates fee for payment settlement capable classes
     * @param paymentSettlementCapable payment settlement capable class
     */
    @Override
    public void validateAndCalculatePaymentSettlement(PaymentSettlementCapable paymentSettlementCapable) {
        paymentSettlementCapable.validate();
        log.info("{} with id: {} is validated", paymentSettlementCapable.getClass().getSimpleName(), paymentSettlementCapable.getId());
        paymentSettlementCapable.calculateFeeAndTimeAtSchool();
        log.info("Fee for {} with id: {} is calculated", paymentSettlementCapable.getClass().getSimpleName(), paymentSettlementCapable.getId());
    }
}
