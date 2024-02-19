package com.school.payment.system.payment.settlement.service.domain.entity;

import com.school.payment.system.domain.valueobject.Money;
import com.school.payment.system.domain.valueobject.ParentId;
import com.school.payment.system.payment.settlement.service.domain.exception.PaymentSettlementDomainException;

import java.util.List;

/**
 * class that represents Parent entity for business logic
 */
public class Parent extends PaymentSettlementCapable<ParentId> {
    private final String firstName;
    private final String lastName;
    private List<Child> children;
    private PaymentSettlement paymentSettlement;

    private Parent(Builder builder) {
        setId(builder.parentId);
        firstName = builder.firstName;
        lastName = builder.lastName;
        children = builder.children;
        paymentSettlement = builder.paymentSettlement;
    }

    /**
     * calculates fee for all parent children
     */
    @Override
    public void calculateFee() {
        children.forEach(child -> child.calculateFee(child.getSchool().getHourPrice()));
        paymentSettlement.setTotalFee(
                children.stream()
                        .map(Child::getChildFee)
                        .reduce(Money.ZERO, Money::add));
    }

    /**
     * validates parent and subclasses
     */
    @Override
    public void validate() {
        validateChildrenAttendances();
        validatePaymentSettlement();
    }

    /**
     * validates payment settlement of parent
     */
    private void validatePaymentSettlement() {
        if (paymentSettlement == null) {
            throw new PaymentSettlementDomainException("PaymentSettlement cannot be null for parent id: " +
                    getId().getValue().toString() +
                    " please contact your system administrator");
        } else {
            paymentSettlement.validate();
        }
    }

    /**
     * validate children attendances
     */
    private void validateChildrenAttendances() {
        children.forEach(Child::validateChildAttendanceDates);
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Child> getChildren() {
        return children;
    }

    public PaymentSettlement getPaymentSettlement() {
        return paymentSettlement;
    }

    @Override
    public void setPaymentSettlement(PaymentSettlement paymentSettlement) {
        this.paymentSettlement = paymentSettlement;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public static final class Builder {
        private ParentId parentId;
        private String firstName;
        private String lastName;
        private List<Child> children;
        private PaymentSettlement paymentSettlement;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ParentId val) {
            parentId = val;
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

        public Builder children(List<Child> val) {
            children = val;
            return this;
        }

        public Builder paymentSettlement(PaymentSettlement val) {
            paymentSettlement = val;
            return this;
        }

        public Parent build() {
            return new Parent(this);
        }
    }
}
