package com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.adapter;

import com.school.payment.system.domain.function.Function3;
import com.school.payment.system.domain.valueobject.BaseId;
import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity.ChildAttendanceEntity;
import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.mapper.ChildAttendanceDataAccessMapper;
import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.repository.ChildAttendanceJpaRepository;
import com.school.payment.system.payment.settlement.service.domain.entity.Parent;
import com.school.payment.system.payment.settlement.service.domain.entity.PaymentSettlementCapable;
import com.school.payment.system.payment.settlement.service.domain.ports.output.repository.ChildAttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * adapter for child repository for postgresql
 */
@Component
@RequiredArgsConstructor
public class ChildAttendanceRepositoryImpl implements ChildAttendanceRepository {

    private final ChildAttendanceJpaRepository childAttendanceJpaRepository;

    private final ChildAttendanceDataAccessMapper childAttendanceDataAccessMapper;

    @Override
    public Optional<PaymentSettlementCapable<BaseId<UUID>>> findBySchoolIdAndAndAttendanceTime(UUID schoolId, LocalDateTime minDate, LocalDateTime maxDate) {
        Optional<List<ChildAttendanceEntity>> childAttendanceEntitiesSelectedMonths =
                childAttendanceJpaRepository.findBySchoolIdAndAndAttendanceTime(schoolId, minDate, maxDate);
        return childAttendanceEntitiesSelectedMonths.map(childAttendanceDataAccessMapper::childAttendanceEntitiesToSchool);
    }

    @Override
    public Optional<PaymentSettlementCapable<BaseId<UUID>>> findByParentIdAndAndAttendanceTime(UUID parentId, LocalDateTime minDate, LocalDateTime maxDate) {
        Optional<List<ChildAttendanceEntity>> childAttendanceEntitiesSelectedMonths =
                childAttendanceJpaRepository.findByParentIdAndAndAttendanceTime(parentId, minDate, maxDate);
        return childAttendanceEntitiesSelectedMonths.map(childAttendanceDataAccessMapper::childAttendanceEntitiesToParent);
    }
}
