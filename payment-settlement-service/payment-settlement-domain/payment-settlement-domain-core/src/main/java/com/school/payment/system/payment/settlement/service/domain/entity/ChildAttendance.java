package com.school.payment.system.payment.settlement.service.domain.entity;

import com.school.payment.system.domain.entity.BaseEntity;
import com.school.payment.system.domain.valueobject.Money;
import com.school.payment.system.payment.settlement.service.domain.util.PaymentSettlementDomainUtils;
import com.school.payment.system.payment.settlement.service.domain.valueobject.ChildAttendanceId;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.school.payment.system.payment.settlement.service.domain.PaymentSettlementDomainConstants.*;

/**
 * Represents single child attendance
 */
public class ChildAttendance extends BaseEntity<ChildAttendanceId> {
    private final LocalDateTime entryDate;
    private final LocalDateTime exitDate;

    public ChildAttendance(LocalDateTime entryDate, LocalDateTime exitDate, ChildAttendanceId childAttendanceId) {
        setId(childAttendanceId);
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

    /**
     * Calculates fee based on hour price and payable school hours
     * @param hourPrice school hour price
     * @return fee for that day
     */
    Money getFee(Money hourPrice) {
        Duration durationBeforeEntry = PaymentSettlementDomainUtils.getDurationIfPositive(
                entryDate.toLocalTime(),
                FREE_SCHOOL_ENTRY_TIME);
        Duration durationAfterEntry = PaymentSettlementDomainUtils.getDurationIfPositive(
                FREE_SCHOOL_EXIT_TIME,
                exitDate.toLocalTime());

        Money feeBeforeEntry = getFeeFromDuration(hourPrice, durationBeforeEntry);
        Money feeAfterEntry = getFeeFromDuration(hourPrice, durationAfterEntry);

        return feeBeforeEntry.add(feeAfterEntry);
    }

    /**
     * calculates fee based on duration of payable hours
     * @param hourPrice school hour price
     * @param duration payable time duration
     * @return price for payable hours and duration of that
     */
    private Money getFeeFromDuration(Money hourPrice, Duration duration) {
        double durationInHours = duration.getSeconds() / (double) SECONDS_IN_HOUR;
        int roundedUpHours = PaymentSettlementDomainUtils.roundUpToNearestInt(durationInHours);
        return hourPrice.multiply(roundedUpHours);
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }
}
