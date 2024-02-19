package com.school.payment.system.payment.settlement.service.domain.entity;

import com.school.payment.system.domain.entity.AggregateRoot;
import com.school.payment.system.payment.settlement.service.domain.entity.entityBehavior.PaymentSettlementApplicable;

/**
 * represent classes that are capable to calculate payment settlement
 * @param <ID> Id of those classes
 */
public abstract class PaymentSettlementCapable<ID> extends AggregateRoot<ID> implements PaymentSettlementApplicable {
}
