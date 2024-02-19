package com.school.payment.system.payment.settlement.service.domain.ports.output.repository;

import com.school.payment.system.domain.function.Function3;
import com.school.payment.system.domain.valueobject.BaseId;
import com.school.payment.system.payment.settlement.service.domain.entity.Parent;
import com.school.payment.system.payment.settlement.service.domain.entity.PaymentSettlementCapable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Output port for child attendance repository
 */
public interface ChildAttendanceRepository {

    Optional<PaymentSettlementCapable<BaseId<UUID>>> findBySchoolIdAndAndAttendanceTime(UUID schoolId, LocalDateTime minDate, LocalDateTime maxDate);

    Optional<PaymentSettlementCapable<BaseId<UUID>>> findByParentIdAndAndAttendanceTime(UUID parentId, LocalDateTime minDate, LocalDateTime maxDate);
}
