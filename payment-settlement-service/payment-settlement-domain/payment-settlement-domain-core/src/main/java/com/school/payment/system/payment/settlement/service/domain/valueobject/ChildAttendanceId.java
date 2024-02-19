package com.school.payment.system.payment.settlement.service.domain.valueobject;

import com.school.payment.system.domain.valueobject.BaseId;

import java.util.UUID;

/**
 * Id class for child attendance
 */
public class ChildAttendanceId extends BaseId<UUID> {
    public ChildAttendanceId(UUID value) {
        super(value);
    }
}
