package com.school.payment.system.payment.settlement.service.domain.dto.parent;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Base DTO class for parent payment settlement response
 */
@Builder
@Getter
@AllArgsConstructor
public class ParentPaymentSettlementResponse {

    @NotNull
    private final String firstName;

    @NotNull
    private final String lastName;

    @NotNull
    private final BigDecimal fee;

    @NotNull
    private final List<Child> children;
}
