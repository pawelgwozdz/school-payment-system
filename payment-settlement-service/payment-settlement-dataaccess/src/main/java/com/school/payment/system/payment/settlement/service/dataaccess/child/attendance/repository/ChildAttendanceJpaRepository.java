package com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.repository;

import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity.ChildAttendanceEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Child attendance JPA repository
 */
@Repository
public interface ChildAttendanceJpaRepository extends JpaRepository<ChildAttendanceEntity, UUID> {

    /**
     * query that returns child entities based on parameters
     * @param schoolId Id of school that child attend
     * @param minDate Minimal date that will be returned from db
     * @param maxDate Maximal date that will be returned from db
     * @return List of child entities
     */
    @Query(value = "FROM ChildAttendanceEntity ca JOIN FETCH ca.child c JOIN FETCH c.school s " +
            "WHERE s.id = :schoolId " +
            "and (ca.entryDate BETWEEN :minDate AND :maxDate) ")
    Optional<List<ChildAttendanceEntity>> findBySchoolIdAndAndAttendanceTime(UUID schoolId, LocalDateTime minDate, LocalDateTime maxDate);

    /**
     * query that returns child entities based on parameters
     * @param parentId Id of parent that child attend
     * @param minDate Minimal date that will be returned from db
     * @param maxDate Maximal date that will be returned from db
     * @return List of child entities
     */
    @Query(value = "FROM ChildAttendanceEntity ca JOIN FETCH ca.child c JOIN FETCH c.parent p JOIN FETCH c.school " +
            "WHERE p.id = :parentId " +
            "and (ca.entryDate BETWEEN :minDate AND :maxDate) ")
    Optional<List<ChildAttendanceEntity>> findByParentIdAndAndAttendanceTime(UUID parentId, LocalDateTime minDate, LocalDateTime maxDate);
}
