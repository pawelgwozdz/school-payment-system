package com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Child attendance db entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "child_attendances")
@Entity
public class ChildAttendanceEntity {

    @Id
    private UUID id;

    private LocalDateTime entryDate;

    private LocalDateTime exitDate;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private ChildEntity child;

}
