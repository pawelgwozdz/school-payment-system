package com.school.payment.system.payment.settlement.service.domain.entity;

import com.school.payment.system.domain.entity.BaseEntity;
import com.school.payment.system.domain.valueobject.Money;
import com.school.payment.system.payment.settlement.service.domain.exception.PaymentSettlementDomainException;
import com.school.payment.system.payment.settlement.service.domain.valueobject.PaymentSettlementId;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 * represents payment settlement
 */
public class PaymentSettlement extends BaseEntity<PaymentSettlementId> {
    private final Year year;
    private final Month month;
    private Money totalFee;

    public PaymentSettlement(Year year, Month month, PaymentSettlementId paymentSettlementId) {
        setId(paymentSettlementId);
        this.year = year;
        this.month = month;
    }

    /**
     * validates correctness of payment settlement
     */
    void validate() {
        if (year == null || month == null) {
            throw new PaymentSettlementDomainException("Year or Month cannot be null for PaymentSettlement id: "
            + getId().getValue().toString() +
                    " please contact your system administrator");
        }
    }

    public void setTotalFee(Money totalFee) {
        this.totalFee = totalFee;
    }

    public Year getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    public Money getTotalFee() {
        return totalFee;
    }
}
