package com.school.payment.system.payment.settlement.service.domain.entity;

import com.school.payment.system.domain.entity.BaseEntity;
import com.school.payment.system.domain.valueobject.ChildId;
import com.school.payment.system.domain.valueobject.Money;
import com.school.payment.system.payment.settlement.service.domain.exception.PaymentSettlementDomainException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents child entity that attends to school
 */
public class Child extends BaseEntity<ChildId> {
    private final String firstName;
    private final String lastName;
    private School school;
    private final List<ChildAttendance> attendances;
    private Money childFee;

    /**
     * Calculate child fee based on attendances
     * @param hourPrice Price per hour for staying over time in school
     */
    void calculateFee(Money hourPrice) {
        childFee = attendances.stream()
                .map(childAttendance -> childAttendance.getFee(hourPrice))
                .reduce(Money.ZERO, Money::add);
    }

    /**
     * Validates child attendances
     */
    public void validateChildAttendanceDates() {
        validateChildAttendancesDayDates();
        validateChildAttendancesDateCopies();
    }

    /**
     * Validates correctness of child attendances day dates
     */
    private void validateChildAttendancesDayDates() {
        attendances.forEach(childAttendance -> {
            if (!childAttendance.getEntryDate().toLocalDate()
                    .equals(childAttendance.getExitDate().toLocalDate())) {
                throw new PaymentSettlementDomainException("There is an error in attendance of child: " +
                        firstName + " " + lastName +
                        ", please contact your system administrator");
            }
        });
    }

    /**
     * checks and validates if there are any replicas of child attendances (multiple attendance's within the same day)
     */
    private void validateChildAttendancesDateCopies() {
        Set<LocalDate> attendancesNoCopy = new HashSet<>();

        attendances.forEach(childAttendance -> {
            if (!attendancesNoCopy.add(childAttendance.getEntryDate().toLocalDate())) {
                throw new PaymentSettlementDomainException("There are duplicated entry dates for child: " +
                        firstName + " " + lastName +
                        ", please contact your system administrator");
            }
        });
    }

    private Child(Builder builder) {
        setId(builder.childId);
        firstName = builder.firstName;
        lastName = builder.lastName;
        school = builder.school;
        attendances = builder.attendances;
    }


    public static final class Builder {
        private ChildId childId;
        private String firstName;
        private String lastName;
        private School school;
        private List<ChildAttendance> attendances;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ChildId val) {
            childId = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder school(School val) {
            school = val;
            return this;
        }

        public Builder attendances(List<ChildAttendance> val) {
            attendances = val;
            return this;
        }

        public Child build() {
            return new Child(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public School getSchool() {
        return school;
    }

    public List<ChildAttendance> getAttendances() {
        return attendances;
    }

    public Money getChildFee() {
        return childFee;
    }

    public void setSchool(School school) {
        this.school = school;
    }


}
