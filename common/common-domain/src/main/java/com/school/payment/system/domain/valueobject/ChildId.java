package com.school.payment.system.domain.valueobject;

import java.util.UUID;

/**
 * Id for Child entity
 */
public class ChildId extends BaseId<UUID> {
    public ChildId(UUID value) {
        super(value);
    }
}
