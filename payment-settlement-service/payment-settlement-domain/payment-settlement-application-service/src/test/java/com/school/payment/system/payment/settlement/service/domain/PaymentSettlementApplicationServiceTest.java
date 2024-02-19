package com.school.payment.system.payment.settlement.service.domain;

import com.school.payment.system.domain.valueobject.ChildId;
import com.school.payment.system.domain.valueobject.ParentId;
import com.school.payment.system.payment.settlement.service.domain.dto.parent.ParentPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.dto.school.SchoolPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.entity.*;
import com.school.payment.system.payment.settlement.service.domain.exception.PaymentSettlementDomainException;
import com.school.payment.system.payment.settlement.service.domain.mapper.PaymentSettlementDataMapper;
import com.school.payment.system.payment.settlement.service.domain.ports.input.service.PaymentSettlementApplicationService;
import com.school.payment.system.payment.settlement.service.domain.ports.output.repository.ChildAttendanceRepository;
import com.school.payment.system.payment.settlement.service.domain.util.DataForPaymentSettlementApplicationServiceTest;
import com.school.payment.system.payment.settlement.service.domain.valueobject.ChildAttendanceId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.TemporalAmount;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = PaymentSettlementTestConfiguration.class)
class PaymentSettlementApplicationServiceTest {

    @Autowired
    private PaymentSettlementApplicationService paymentSettlementApplicationService;

    @Autowired
    private PaymentSettlementDataMapper paymentSettlementDataMapper;

    @Autowired
    private ChildAttendanceRepository childAttendanceRepository;

    private final UUID parent_id = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb26");
    private final UUID school_id = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb21");

    private School schoolOk;
    private School schoolPaymentSettlementDomainException;
    private Parent parentOk;
    private Parent parentPaymentSettlementDomainException;
    private School schoolForParent;


    @BeforeAll
    public void init() {
        schoolOk = DataForPaymentSettlementApplicationServiceTest.getSchoolOk(school_id);

        schoolPaymentSettlementDomainException = DataForPaymentSettlementApplicationServiceTest
                .getSchoolPaymentSettlementDomainException(school_id);

        schoolForParent = DataForPaymentSettlementApplicationServiceTest.getSchoolForParent(school_id);

        parentOk = DataForPaymentSettlementApplicationServiceTest.getParentOk(parent_id, schoolForParent);

        parentPaymentSettlementDomainException = DataForPaymentSettlementApplicationServiceTest
                .getParentPaymentSettlementDomainException(parent_id, schoolForParent);
    }

    @Test
    public void testGetSchoolPaymentSettlement_expectPositiveResponse() {

        //GIVEN:
        Year year = Year.of(2023);
        Month month = Month.APRIL;
        YearMonth yearMonth = YearMonth.of(year.getValue(), month.getValue());
        LocalDateTime minDate = LocalDateTime.of(yearMonth.atDay(1), LocalTime.MIN);
        LocalDateTime maxDate = LocalDateTime.of(yearMonth.atEndOfMonth(), LocalTime.MAX);
        when(childAttendanceRepository.findBySchoolIdAndAndAttendanceTime(school_id, minDate, maxDate)).thenReturn(Optional.of((PaymentSettlementCapable) schoolOk));

        //WHEN:
        SchoolPaymentSettlementResponse schoolPaymentSettlementResponse = paymentSettlementApplicationService.getSchoolPaymentSettlement(school_id, year, month);

        //THEN:
        Optional<com.school.payment.system.payment.settlement.service.domain.dto.school.Child> child1 =
                schoolPaymentSettlementResponse.getChildren().stream()
                        .filter(child -> child.getFirstName().equals("Gregory"))
                        .findFirst();
        Optional<com.school.payment.system.payment.settlement.service.domain.dto.school.Child> child2 =
                schoolPaymentSettlementResponse.getChildren().stream()
                        .filter(child -> child.getFirstName().equals("Martin"))
                        .findFirst();

        assertEquals(schoolOk.getName(), schoolPaymentSettlementResponse.getSchoolName());
        assertEquals(new BigDecimal("50.00"), schoolPaymentSettlementResponse.getFee());
        assertTrue(child1.isPresent());
        assertTrue(child2.isPresent());
        assertEquals(new BigDecimal("30.00"), child1.get().getFee());
        assertEquals(new BigDecimal("20.00"), child2.get().getFee());
        assertEquals(Duration.ofSeconds(32462), child1.get().getTimeAtSchool());
        assertEquals(Duration.ofSeconds(18061), child2.get().getTimeAtSchool());
    }

    @Test
    public void testGetSchoolPaymentSettlement_expectPaymentSettlementDomainException() {

        //GIVEN:
        Year year = Year.of(2023);
        Month month = Month.APRIL;
        YearMonth yearMonth = YearMonth.of(year.getValue(), month.getValue());
        LocalDateTime minDate = LocalDateTime.of(yearMonth.atDay(1), LocalTime.MIN);
        LocalDateTime maxDate = LocalDateTime.of(yearMonth.atEndOfMonth(), LocalTime.MAX);
        when(childAttendanceRepository.findBySchoolIdAndAndAttendanceTime(school_id, minDate, maxDate)).thenReturn(Optional.of((PaymentSettlementCapable) schoolPaymentSettlementDomainException));

        //WHEN & THEN:
        assertThrows(PaymentSettlementDomainException.class, () -> paymentSettlementApplicationService.getSchoolPaymentSettlement(school_id, year, month));
    }

    @Test
    public void testGetParentPaymentSettlement_expectPositive() {

        //GIVEN:
        Year year = Year.of(2023);
        Month month = Month.APRIL;
        YearMonth yearMonth = YearMonth.of(year.getValue(), month.getValue());
        LocalDateTime minDate = LocalDateTime.of(yearMonth.atDay(1), LocalTime.MIN);
        LocalDateTime maxDate = LocalDateTime.of(yearMonth.atEndOfMonth(), LocalTime.MAX);
        when(childAttendanceRepository.findByParentIdAndAndAttendanceTime(parent_id, minDate, maxDate)).thenReturn(Optional.of((PaymentSettlementCapable) parentOk));


        //WHEN:
        ParentPaymentSettlementResponse parentPaymentSettlementResponse = paymentSettlementApplicationService.getParentPaymentSettlement(parent_id, year, month);

        //THEN:
        Optional<com.school.payment.system.payment.settlement.service.domain.dto.parent.Child> child1 =
                parentPaymentSettlementResponse.getChildren().stream()
                        .filter(child -> child.getFirstName().equals("Gregory"))
                        .findFirst();
        Optional<com.school.payment.system.payment.settlement.service.domain.dto.parent.Child> child2 =
                parentPaymentSettlementResponse.getChildren().stream()
                        .filter(child -> child.getFirstName().equals("Martin"))
                        .findFirst();

        assertTrue(child1.isPresent());
        assertTrue(child2.isPresent());
        assertEquals(schoolForParent.getName(), child1.get().getSchool().getName());
        assertEquals(schoolForParent.getName(), child2.get().getSchool().getName());
        assertEquals(new BigDecimal("25.00"), parentPaymentSettlementResponse.getFee());
        assertEquals(new BigDecimal("15.00"), child1.get().getFee());
        assertEquals(new BigDecimal("10.00"), child2.get().getFee());
        assertEquals(Duration.ofSeconds(32462), child1.get().getTimeAtSchool());
        assertEquals(Duration.ofSeconds(18061), child2.get().getTimeAtSchool());
    }


    @Test
    public void testGetParentPaymentSettlement_expectPaymentSettlementDomainException() {

        //GIVEN:
        Year year = Year.of(2023);
        Month month = Month.APRIL;
        YearMonth yearMonth = YearMonth.of(year.getValue(), month.getValue());
        LocalDateTime minDate = LocalDateTime.of(yearMonth.atDay(1), LocalTime.MIN);
        LocalDateTime maxDate = LocalDateTime.of(yearMonth.atEndOfMonth(), LocalTime.MAX);
        when(childAttendanceRepository.findByParentIdAndAndAttendanceTime(parent_id, minDate, maxDate)).thenReturn(Optional.of((PaymentSettlementCapable) parentPaymentSettlementDomainException));

        //WHEN & THEN:
        assertThrows(PaymentSettlementDomainException.class, () -> paymentSettlementApplicationService.getParentPaymentSettlement(parent_id, year, month));
    }
}