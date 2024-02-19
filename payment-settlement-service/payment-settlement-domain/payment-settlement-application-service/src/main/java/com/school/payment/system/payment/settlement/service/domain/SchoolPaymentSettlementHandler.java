package com.school.payment.system.payment.settlement.service.domain;

import com.school.payment.system.domain.valueobject.BaseId;
import com.school.payment.system.domain.valueobject.SchoolId;
import com.school.payment.system.payment.settlement.service.domain.dto.school.SchoolPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.entity.AttendanceTime;
import com.school.payment.system.payment.settlement.service.domain.entity.PaymentSettlementCapable;
import com.school.payment.system.payment.settlement.service.domain.entity.School;
import com.school.payment.system.payment.settlement.service.domain.mapper.PaymentSettlementDataMapper;
import com.school.payment.system.payment.settlement.service.domain.ports.output.repository.ChildAttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.Year;
import java.util.Optional;
import java.util.UUID;

/**
 * Handler for all school methods for payment settlement application service
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SchoolPaymentSettlementHandler {

    private final ChildAttendanceRepository childAttendanceRepository;

    private final PaymentSettlementDataMapper paymentSettlementDataMapper;

    private final PaymentSettlementHelper paymentSettlementHelper;

    /**
     * Process school payment settlement
     * @param schoolId Id of processed school
     * @param year Processed year
     * @param month Processed month
     * @return School payment settlement
     */
    public SchoolPaymentSettlementResponse getSchoolPaymentSettlement(UUID schoolId, Year year, Month month) {

        AttendanceTime attendanceTime = paymentSettlementHelper.createAndCalculateAttendanceTime(year, month);

        PaymentSettlementCapable paymentSettlementCapableCalculated = paymentSettlementHelper.getCalculatedFee(schoolId, attendanceTime,
                childAttendanceRepository::findBySchoolIdAndAndAttendanceTime);

        if (!(paymentSettlementCapableCalculated instanceof School school)) {
            throw new ClassCastException("Unable to cast PaymentSettlementCapable to School in getSchoolPaymentSettlement");
        }

        return paymentSettlementDataMapper.SchoolToSchoolPaymentSettlementResponse(school);
    }
}
