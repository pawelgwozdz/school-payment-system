package com.school.payment.system.payment.settlement.service.domain.ports.input.service;

import com.school.payment.system.payment.settlement.service.domain.dto.parent.ParentPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.dto.school.SchoolPaymentSettlementResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

/**
 * Input port for payment settlement application service
 */
public interface PaymentSettlementApplicationService {

    SchoolPaymentSettlementResponse getSchoolPaymentSettlement(@Valid @NotNull UUID schoolId, @NotNull Year year, @NotNull Month month);

    ParentPaymentSettlementResponse getParentPaymentSettlement(@Valid @NotNull UUID parentId, @NotNull Year year, @NotNull Month month);
}
