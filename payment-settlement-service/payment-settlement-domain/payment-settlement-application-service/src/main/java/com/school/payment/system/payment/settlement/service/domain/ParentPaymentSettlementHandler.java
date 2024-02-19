package com.school.payment.system.payment.settlement.service.domain;

import com.school.payment.system.payment.settlement.service.domain.dto.parent.ParentPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.entity.AttendanceTime;
import com.school.payment.system.payment.settlement.service.domain.entity.Parent;
import com.school.payment.system.payment.settlement.service.domain.entity.PaymentSettlementCapable;
import com.school.payment.system.payment.settlement.service.domain.mapper.PaymentSettlementDataMapper;
import com.school.payment.system.payment.settlement.service.domain.ports.output.repository.ChildAttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

/**
 * Handler for parent methods of payment settlement application service
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ParentPaymentSettlementHandler {

    private final PaymentSettlementDataMapper paymentSettlementDataMapper;

    private final ChildAttendanceRepository childAttendanceRepository;

    private final PaymentSettlementHelper paymentSettlementHelper;

    /**
     * Process school payment settlement
     * @param parentId Id of processed parent
     * @param year Processed year
     * @param month Processed month
     * @return School payment settlement
     */
    public ParentPaymentSettlementResponse getParentPaymentSettlement(UUID parentId, Year year, Month month) {

        AttendanceTime attendanceTime = paymentSettlementHelper.createAndCalculateAttendanceTime(year, month);

        PaymentSettlementCapable paymentSettlementCapableCalculated = paymentSettlementHelper.getCalculatedFee(parentId, attendanceTime,
                childAttendanceRepository::findByParentIdAndAndAttendanceTime);

        if (!(paymentSettlementCapableCalculated instanceof Parent parent)) {
            throw new ClassCastException("Unable to cast PaymentSettlementCapable to Parent in getParentPaymentSettlement");
        }

        return paymentSettlementDataMapper.ParentToParentPaymentSettlementResponse(parent);
    }
}
