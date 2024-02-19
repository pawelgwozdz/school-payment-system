package com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity;

import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity.ChildEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Parent db entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parents")
@Entity
public class ParentEntity {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ChildEntity> child;
}
