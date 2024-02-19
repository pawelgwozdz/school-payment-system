package com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.mapper;

import com.school.payment.system.domain.valueobject.*;
import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity.ChildAttendanceEntity;
import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity.ParentEntity;
import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.entity.SchoolEntity;
import com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.exception.ChildAttendanceDataAccessException;
import com.school.payment.system.payment.settlement.service.domain.entity.*;
import com.school.payment.system.payment.settlement.service.domain.valueobject.ChildAttendanceId;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * mapper between application service and data access module
 */
@Component
public class ChildAttendanceDataAccessMapper {
    public PaymentSettlementCapable childAttendanceEntitiesToSchool(List<ChildAttendanceEntity> childAttendanceEntities) {
        if (childAttendanceEntities.size() == 0) {
            return null;
        }
        SchoolEntity schoolEntity = childAttendanceEntities.stream()
                .findAny().orElseThrow(() ->
                        new ChildAttendanceDataAccessException("Child attendance could not be found"))
                .getChild().getSchool();

        School school = School.Builder.builder()
                .name(schoolEntity.getName())
                .id(new SchoolId(schoolEntity.getId()))
                .hourPrice(new Money(schoolEntity.getHourPrice()))
                .build();

        List<Child> children = new ArrayList<>();
        childAttendanceEntities.forEach(childAttendanceEntity -> {

            ChildAttendance childAttendance = new ChildAttendance(childAttendanceEntity.getEntryDate(), childAttendanceEntity.getExitDate(), new ChildAttendanceId(childAttendanceEntity.getId()));

            Child childTemp = Child.Builder.builder()
                    .school(school)
                    .attendances(new LinkedList<>(List.of(childAttendance)))
                    .firstName(childAttendanceEntity.getChild().getFirstName())
                    .lastName(childAttendanceEntity.getChild().getLastName())
                    .id(new ChildId(childAttendanceEntity.getChild().getId()))
                    .build();
                Optional<Child> child = children.stream().filter(child1 -> child1.equals(childTemp)).findAny();
                if (child.isPresent()) {
                    child.get().getAttendances().add(childAttendance);
                } else {
                    children.add(childTemp);
                }
        });

        school.setChildren(children);
        return school;
    }

    public PaymentSettlementCapable childAttendanceEntitiesToParent(List<ChildAttendanceEntity> childAttendanceEntities) {
        if (childAttendanceEntities.size() == 0) {
            return null;
        }

        ParentEntity parentEntity = childAttendanceEntities.stream()
                .findAny().orElseThrow(() ->
                        new ChildAttendanceDataAccessException("Child attendance could not be found"))
                .getChild().getParent();

        Parent parent = Parent.Builder.builder()
                .id(new ParentId(parentEntity.getId()))
                .firstName(parentEntity.getFirstName())
                .lastName(parentEntity.getLastName())
                .build();

        List<Child> children = new ArrayList<>();
        childAttendanceEntities.forEach(childAttendanceEntity -> {



            ChildAttendance childAttendance = new ChildAttendance(childAttendanceEntity.getEntryDate(), childAttendanceEntity.getExitDate(), new ChildAttendanceId(childAttendanceEntity.getId()));

            Child childTemp = Child.Builder.builder()
                    .attendances(new LinkedList<>(List.of(childAttendance)))
                    .firstName(childAttendanceEntity.getChild().getFirstName())
                    .lastName(childAttendanceEntity.getChild().getLastName())
                    .id(new ChildId(childAttendanceEntity.getChild().getId()))
                    .build();
            Optional<Child> child = children.stream().filter(child1 -> child1.equals(childTemp)).findAny();
            if (child.isPresent()) {
                child.get().getAttendances().add(childAttendance);
            } else {
                SchoolEntity schoolEntity = childAttendanceEntity.getChild().getSchool();
                School school = School.Builder.builder()
                        .name(schoolEntity.getName())
                        .id(new SchoolId(schoolEntity.getId()))
                        .hourPrice(new Money(schoolEntity.getHourPrice()))
                        .build();
                childTemp.setSchool(school);
                children.add(childTemp);
            }
        });

        parent.setChildren(children);
        return parent;
    }
}
