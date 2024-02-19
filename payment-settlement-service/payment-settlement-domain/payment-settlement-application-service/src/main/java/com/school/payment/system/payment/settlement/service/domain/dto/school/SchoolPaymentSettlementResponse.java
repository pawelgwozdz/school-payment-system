package com.school.payment.system.payment.settlement.service.domain.dto.school;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Base DTO class for school payment settlement response
 */
@Getter
@AllArgsConstructor
@Builder
public class SchoolPaymentSettlementResponse {

    @NotNull
    private final String schoolName;

    @NotNull
    private final BigDecimal fee;

    @NotNull
    private final List<Child> children;
}
