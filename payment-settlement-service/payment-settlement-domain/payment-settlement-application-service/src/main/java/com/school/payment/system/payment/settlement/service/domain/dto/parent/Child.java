package com.school.payment.system.payment.settlement.service.domain.dto.parent;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Dto for child
 */
@Getter
@Builder
@AllArgsConstructor
public class Child {

    @NotNull
    private final String firstName;

    @NotNull
    private final String lastName;

    @NotNull
    private final BigDecimal fee;

    @NotNull
    private final School school;
}
