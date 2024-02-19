package com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity;

import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity.ChildEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * School db entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schools")
@Entity
public class SchoolEntity {

    @Id
    private UUID id;

    private BigDecimal hourPrice;

    private String name;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<ChildEntity> child;
}
