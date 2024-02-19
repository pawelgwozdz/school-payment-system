package com.school.payment.system.payment.settlement.service.domain.entity;

import com.school.payment.system.domain.valueobject.Money;
import com.school.payment.system.domain.valueobject.SchoolId;

import java.util.List;

/**
 * Represents school domain entity for business logic
 */
public class School extends PaymentSettlementCapable<SchoolId> {
    private final String name;
    private final Money hourPrice;
    private List<Child> children;
    private PaymentSettlement paymentSettlement;

    private School(Builder builder) {
        setId(builder.schoolId);
        name = builder.name;
        hourPrice = builder.hourPrice;
        children = builder.children;
        paymentSettlement = builder.paymentSettlement;
    }


    /**
     * Calculates fees for all children attending school
     */
    @Override
    public void calculateFeeAndTimeAtSchool() {
        children.forEach(child -> child.calculateFee(hourPrice));
        paymentSettlement.setTotalFee(
                children.stream()
                        .map(Child::getChildFee)
                        .reduce(Money.ZERO, Money::add));
        children.forEach(Child::calculateTimeAtSchool);
    }

    /**
     * Validates school
     */
    @Override
    public void validate() {
        validateChildrenAttendances();
    }

    /**
     * Validates children attendances in school
     */
    private void validateChildrenAttendances() {
        children.forEach(Child::validateChildAttendanceDates);
    }



    public String getName() {
        return name;
    }

    public Money getHourPrice() {
        return hourPrice;
    }

    public List<Child> getChildren() {
        return children;
    }

    @Override
    public void setPaymentSettlement(PaymentSettlement paymentSettlement) {
        this.paymentSettlement = paymentSettlement;
    }

    public PaymentSettlement getPaymentSettlement() {
        return paymentSettlement;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public static final class Builder {
        private SchoolId schoolId;
        private String name;
        private Money hourPrice;
        private List<Child> children;
        private PaymentSettlement paymentSettlement;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(SchoolId val) {
            schoolId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder hourPrice(Money val) {
            hourPrice = val;
            return this;
        }

        public Builder children(List<Child> val) {
            children = val;
            return this;
        }

        public Builder paymentSettlement(PaymentSettlement val) {
            paymentSettlement = val;
            return this;
        }

        public School build() {
            return new School(this);
        }
    }
}
