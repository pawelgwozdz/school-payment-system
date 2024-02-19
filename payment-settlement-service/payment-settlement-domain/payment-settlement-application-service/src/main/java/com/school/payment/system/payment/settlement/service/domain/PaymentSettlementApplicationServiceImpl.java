package com.school.payment.system.payment.settlement.service.domain;

import com.school.payment.system.payment.settlement.service.domain.dto.parent.ParentPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.dto.school.SchoolPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.entity.Parent;
import com.school.payment.system.payment.settlement.service.domain.entity.School;
import com.school.payment.system.payment.settlement.service.domain.ports.input.service.PaymentSettlementApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

/**
 * implementation of payment settlement application service input port
 */
@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class PaymentSettlementApplicationServiceImpl implements PaymentSettlementApplicationService {

    private final SchoolPaymentSettlementHandler schoolPaymentSettlementHandler;

    private final ParentPaymentSettlementHandler parentPaymentSettlementHandler;

    @Override
    public SchoolPaymentSettlementResponse getSchoolPaymentSettlement(UUID schoolId, Year year, Month month) {
        return schoolPaymentSettlementHandler.getSchoolPaymentSettlement(schoolId, year, month);
    }

    @Override
    public ParentPaymentSettlementResponse getParentPaymentSettlement(UUID parentId, Year year, Month month) {
        return parentPaymentSettlementHandler.getParentPaymentSettlement(parentId, year, month);
    }
}
