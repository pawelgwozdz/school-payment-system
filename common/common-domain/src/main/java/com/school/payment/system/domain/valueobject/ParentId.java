package com.school.payment.system.domain.valueobject;

import java.util.UUID;

/**
 * Id for Parent entity
 */
public class ParentId extends BaseId<UUID> {
    public ParentId(UUID value) {
        super(value);
    }
}
