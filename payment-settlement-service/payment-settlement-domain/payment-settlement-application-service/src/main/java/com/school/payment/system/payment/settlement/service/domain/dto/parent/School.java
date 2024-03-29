package com.school.payment.system.payment.settlement.service.domain.dto.parent;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@AllArgsConstructor
public class School {

    private School() {
        name = null;
        id = null;
    }

    @NotNull
    private final UUID id;

    @NotNull
    private final String name;
}
