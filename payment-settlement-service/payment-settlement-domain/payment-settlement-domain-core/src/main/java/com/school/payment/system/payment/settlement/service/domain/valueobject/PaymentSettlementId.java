package com.school.payment.system.payment.settlement.service.domain.valueobject;

import com.school.payment.system.domain.valueobject.BaseId;

import java.util.UUID;

/**
 * id class for payment settlement
 */
public class PaymentSettlementId extends BaseId<UUID> {
    public PaymentSettlementId(UUID value) {
        super(value);
    }
}
