package com.school.payment.system.payment.settlement.service.domain;

import com.school.payment.system.domain.function.Function3;
import com.school.payment.system.domain.valueobject.BaseId;
import com.school.payment.system.payment.settlement.service.domain.entity.AttendanceTime;
import com.school.payment.system.payment.settlement.service.domain.entity.PaymentSettlement;
import com.school.payment.system.payment.settlement.service.domain.entity.PaymentSettlementCapable;
import com.school.payment.system.payment.settlement.service.domain.exception.ParentNotFoundException;
import com.school.payment.system.payment.settlement.service.domain.mapper.PaymentSettlementDataMapper;
import com.school.payment.system.payment.settlement.service.domain.valueobject.PaymentSettlementId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Optional;
import java.util.UUID;

/**
 * helper for payment settlement logic used for all payment settlements mutual code
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentSettlementHelper {

    private final PaymentSettlementDomainService paymentSettlementDomainService;

    private final PaymentSettlementDataMapper paymentSettlementDataMapper;

    /**
     * Finds data for paymentSettlementCapable entity in db and calculates fee
     * @param id paymentSettlementCapable id
     * @param attendanceTime attendance time to calculate
     * @param paymentSettlementApplicableFunction3 function that searches in fb for payment settlement capable entity data
     * @return paymentSettlementCapable with calculated data
     */
    public PaymentSettlementCapable<BaseId<UUID>> getCalculatedFee(UUID id, AttendanceTime attendanceTime, Function3<UUID, LocalDateTime, LocalDateTime, Optional<PaymentSettlementCapable<BaseId<UUID>>>> paymentSettlementApplicableFunction3) {

        Optional<PaymentSettlementCapable<BaseId<UUID>>> paymentSettlementCapableResult =
                getPaymentSettlementCapableResult(id, attendanceTime, paymentSettlementApplicableFunction3);

        if (paymentSettlementCapableResult.isEmpty()) {
            dataNotFound(id, attendanceTime);
        }

        PaymentSettlementCapable<BaseId<UUID>> paymentSettlementCapable = paymentSettlementCapableResult.get();

        createPaymentSettlement(attendanceTime, paymentSettlementCapable);

        paymentSettlementDomainService.validateAndCalculatePaymentSettlement(paymentSettlementCapable);

        return paymentSettlementCapable;
    }

    /**
     * Calculates first and last date in chosen month
     * @param year Chosen year
     * @param month Chosen month
     * @return Attendance time with calculated data
     */
    public AttendanceTime createAndCalculateAttendanceTime(Year year, Month month) {
        AttendanceTime attendanceTime = paymentSettlementDataMapper.yearAndMonthToAttendanceTime(year, month);
        attendanceTime.calculateMinAndMaxDate();

        return attendanceTime;
    }

    /**
     * Throws error if data not found for chosen id and attendance time
     * @param id Id of payment settlement capable entity
     * @param attendanceTime Attendance time
     */
    private void dataNotFound(UUID id, AttendanceTime attendanceTime) {
        log.info("child attendance entities for given data not found, request id: {}, year: {}, month: {}",
                id.toString(),
                attendanceTime.getYear(),
                attendanceTime.getMonth());
        throw new ParentNotFoundException(
                "child attendance entities for given data not found, request id: " + id +
                        " year: " + attendanceTime.getYear() +
                        " month: " + attendanceTime.getMonth());
    }

    /**
     * Finds data for paymentSettlementCapable entity in db
     * @param id PaymentSettlementCapable id
     * @param attendanceTime Attendance time
     * @param paymentSettlementApplicableFunction3 Function that searches in fb for payment settlement capable entity data
     * @return PaymentSettlementCapable data
     */
    private Optional<PaymentSettlementCapable<BaseId<UUID>>> getPaymentSettlementCapableResult(UUID id, AttendanceTime attendanceTime, Function3<UUID, LocalDateTime, LocalDateTime, Optional<PaymentSettlementCapable<BaseId<UUID>>>> paymentSettlementApplicableFunction3) {
        return paymentSettlementApplicableFunction3.apply(id, attendanceTime.getMinDate(), attendanceTime.getMaxDate());
    }

    /**
     * Creates PaymentSettlement entity based on given data and sets in PaymentSettlementCapable
     * @param attendanceTime Attendance time
     * @param paymentSettlementCapable PaymentSettlementCapable entity
     */
    private void createPaymentSettlement(AttendanceTime attendanceTime, PaymentSettlementCapable<BaseId<UUID>> paymentSettlementCapable) {
        PaymentSettlement paymentSettlement = new PaymentSettlement(attendanceTime.getYear(), attendanceTime.getMonth(), new PaymentSettlementId(UUID.randomUUID()));
        paymentSettlementCapable.setPaymentSettlement(paymentSettlement);

        log.info("PaymentSettlement set with id: {}, for {} id: {}",
                paymentSettlement.getId().getValue().toString(),
                paymentSettlementCapable.getClass().getSimpleName(),
                paymentSettlementCapable.getId().getValue().toString());
    }

}
