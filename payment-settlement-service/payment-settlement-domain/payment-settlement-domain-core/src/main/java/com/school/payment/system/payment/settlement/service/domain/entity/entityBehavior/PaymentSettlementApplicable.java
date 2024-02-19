package com.school.payment.system.payment.settlement.service.domain.entity.entityBehavior;

import com.school.payment.system.payment.settlement.service.domain.entity.PaymentSettlement;

/**
 * class that represents behaviors of classes that will calculate and validate payment settlements
 */
public interface PaymentSettlementApplicable {

    void calculateFeeAndTimeAtSchool();

    void validate();

    void setPaymentSettlement(PaymentSettlement paymentSettlement);

}
