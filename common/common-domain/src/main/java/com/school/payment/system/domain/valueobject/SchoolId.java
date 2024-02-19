package com.school.payment.system.domain.valueobject;

import java.util.UUID;

/**
 * Id class for School entity
 */
public class SchoolId extends BaseId<UUID> {
    public SchoolId(UUID value) {
        super(value);
    }
}
