package com.school.payment.system.payment.settlement.service.domain.valueobject;

import com.school.payment.system.domain.entity.BaseEntity;
import com.school.payment.system.domain.valueobject.BaseId;

import java.util.UUID;

/**
 * id class for attendance time
 */
public class AttendanceTimeId extends BaseId<UUID> {
    public AttendanceTimeId(UUID value) {
        super(value);
    }
}
