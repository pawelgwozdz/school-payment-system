package com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Child db entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "children")
@Entity
public class ChildEntity {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private ParentEntity parent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SCHOOL_ID")
    private SchoolEntity school;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<ChildAttendanceEntity> childAttendanceEntities;
}
