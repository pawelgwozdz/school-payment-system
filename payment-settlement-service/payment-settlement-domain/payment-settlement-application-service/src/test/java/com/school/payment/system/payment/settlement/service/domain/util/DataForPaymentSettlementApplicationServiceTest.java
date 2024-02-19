package com.school.payment.system.payment.settlement.service.domain.util;

import com.school.payment.system.domain.valueobject.ChildId;
import com.school.payment.system.domain.valueobject.Money;
import com.school.payment.system.domain.valueobject.ParentId;
import com.school.payment.system.domain.valueobject.SchoolId;
import com.school.payment.system.payment.settlement.service.domain.entity.Child;
import com.school.payment.system.payment.settlement.service.domain.entity.ChildAttendance;
import com.school.payment.system.payment.settlement.service.domain.entity.Parent;
import com.school.payment.system.payment.settlement.service.domain.entity.School;
import com.school.payment.system.payment.settlement.service.domain.valueobject.ChildAttendanceId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

public class DataForPaymentSettlementApplicationServiceTest {

    public static School getSchoolOk(UUID school_id) {
        return com.school.payment.system.payment.settlement.service.domain.entity.School.Builder.builder()
                .id(new SchoolId(school_id))
                .hourPrice(new Money(BigDecimal.valueOf(10)))
                .name("School 1")
                .children(List.of(Child.Builder.builder()
                                .firstName("Gregory")
                                .lastName("Johnson")
                                .id(new ChildId(UUID.randomUUID()))
                                .attendances(List.of(new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 22, 6, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 22, 12, 1, 0),
                                                new ChildAttendanceId(UUID.randomUUID())),
                                        new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 2, 8, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 2, 13, 0, 0),
                                                new ChildAttendanceId(UUID.randomUUID()))))
                                .build(),
                        Child.Builder.builder()
                                .firstName("Martin")
                                .lastName("Martini")
                                .id(new ChildId(UUID.randomUUID()))
                                .attendances(List.of(
                                        new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 15, 6, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 15, 12, 1, 0),
                                                new ChildAttendanceId(UUID.randomUUID()))))
                                .build()))
                .build();
    }

    public static School getSchoolPaymentSettlementDomainException(UUID school_id) {
        return School.Builder.builder()
                .id(new SchoolId(school_id))
                .hourPrice(new Money(BigDecimal.valueOf(10)))
                .name("School 1")
                .children(List.of(Child.Builder.builder()
                                .firstName("Gregory")
                                .lastName("Johnson")
                                .id(new ChildId(UUID.randomUUID()))
                                .attendances(List.of(new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 22, 6, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 23, 12, 1, 0),
                                                new ChildAttendanceId(UUID.randomUUID())),
                                        new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 2, 8, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 2, 13, 0, 0),
                                                new ChildAttendanceId(UUID.randomUUID()))))
                                .build(),
                        Child.Builder.builder()
                                .firstName("Martin")
                                .lastName("Martini")
                                .id(new ChildId(UUID.randomUUID()))
                                .attendances(List.of(
                                        new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 15, 6, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 15, 12, 1, 0),
                                                new ChildAttendanceId(UUID.randomUUID()))))
                                .build()))
                .build();
    }

    public static School getSchoolForParent(UUID school_id) {
        return School.Builder.builder()
                .id(new SchoolId(school_id))
                .hourPrice(new Money(BigDecimal.valueOf(5)))
                .name("School 1")
                .build();
    }

    public static Parent getParentOk(UUID parent_id, School school) {
        return Parent.Builder.builder()
                .id(new ParentId(parent_id))
                .firstName("Jake")
                .lastName("Paul")
                .children(List.of(Child.Builder.builder()
                                .firstName("Gregory")
                                .lastName("Johnson")
                                .school(school)
                                .id(new ChildId(UUID.randomUUID()))
                                .attendances(List.of(new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 22, 6, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 22, 12, 1, 0),
                                                new ChildAttendanceId(UUID.randomUUID())),
                                        new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 2, 8, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 2, 13, 0, 0),
                                                new ChildAttendanceId(UUID.randomUUID()))))
                                .build(),
                        Child.Builder.builder()
                                .firstName("Martin")
                                .lastName("Martini")
                                .school(school)
                                .id(new ChildId(UUID.randomUUID()))
                                .attendances(List.of(
                                        new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 15, 6, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 15, 12, 1, 0),
                                                new ChildAttendanceId(UUID.randomUUID()))))
                                .build()))
                .build();
    }

    public static Parent getParentPaymentSettlementDomainException(UUID parent_id, School school) {
        return Parent.Builder.builder()
                .id(new ParentId(parent_id))
                .firstName("Jake")
                .lastName("Paul")
                .children(List.of(Child.Builder.builder()
                                .firstName("Gregory")
                                .lastName("Johnson")
                                .school(school)
                                .id(new ChildId(UUID.randomUUID()))
                                .attendances(List.of(new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 22, 6, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 22, 12, 1, 0),
                                                new ChildAttendanceId(UUID.randomUUID())),
                                        new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 2, 8, 59, 59),
                                                LocalDateTime.of(2023, Month.JUNE, 2, 13, 0, 0),
                                                new ChildAttendanceId(UUID.randomUUID()))))
                                .build(),
                        Child.Builder.builder()
                                .firstName("Martin")
                                .lastName("Martini")
                                .school(school)
                                .id(new ChildId(UUID.randomUUID()))
                                .attendances(List.of(
                                        new ChildAttendance(
                                                LocalDateTime.of(2023, Month.APRIL, 15, 6, 59, 59),
                                                LocalDateTime.of(2023, Month.APRIL, 15, 12, 1, 0),
                                                new ChildAttendanceId(UUID.randomUUID()))))
                                .build()))
                .build();
    }
}
