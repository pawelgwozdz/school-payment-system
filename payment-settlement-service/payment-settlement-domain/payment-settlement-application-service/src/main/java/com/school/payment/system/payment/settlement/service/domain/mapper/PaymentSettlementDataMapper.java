package com.school.payment.system.payment.settlement.service.domain.mapper;

import com.school.payment.system.payment.settlement.service.domain.dto.parent.Child;
import com.school.payment.system.payment.settlement.service.domain.dto.parent.ParentPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.dto.parent.School;
import com.school.payment.system.payment.settlement.service.domain.dto.school.SchoolPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.entity.AttendanceTime;
import com.school.payment.system.payment.settlement.service.domain.entity.Parent;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.Year;
import java.util.stream.Collectors;

/**
 * Mapper between DTO entities and domain entities
 */
@Component
public class PaymentSettlementDataMapper {

    public ParentPaymentSettlementResponse ParentToParentPaymentSettlementResponse(Parent parent) {
        return ParentPaymentSettlementResponse.builder()
                .id(parent.getId().getValue())
                .firstName(parent.getFirstName())
                .lastName(parent.getLastName())
                .fee(parent.getPaymentSettlement().getTotalFee().getAmount())
                .children(parent.getChildren().stream().map(child ->
                        Child.builder()
                                .id(child.getId().getValue())
                                .fee(child.getChildFee().getAmount())
                                .firstName(child.getFirstName())
                                .lastName(child.getLastName())
                                .timeAtSchool(child.getTimeAtSchool())
                                .school(new School(
                                        child.getSchool().getId().getValue(),
                                        child.getSchool().getName()))
                                .build()).collect(Collectors.toList()))
                .build();
    }

    public SchoolPaymentSettlementResponse SchoolToSchoolPaymentSettlementResponse(com.school.payment.system.payment.settlement.service.domain.entity.School school) {
        return SchoolPaymentSettlementResponse.builder()
                .id(school.getId().getValue())
                .schoolName(school.getName())
                .fee(school.getPaymentSettlement().getTotalFee().getAmount())
                .children(school.getChildren().stream().map(child ->
                        com.school.payment.system.payment.settlement.service.domain.dto.school.Child.builder()
                                .id(child.getId().getValue())
                                .fee(child.getChildFee().getAmount())
                                .firstName(child.getFirstName())
                                .lastName(child.getLastName())
                                .timeAtSchool(child.getTimeAtSchool())
                                .build()).collect(Collectors.toList()))
                .build();
    }

    public AttendanceTime yearAndMonthToAttendanceTime(Year year, Month month) {
        return new AttendanceTime(year, month);
    }
}
