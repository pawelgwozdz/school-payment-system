package com.school.payment.system.payment.settlement.service.domain;

import java.time.LocalTime;

/**
 * Contains payment settlement constants
 */
public class PaymentSettlementDomainConstants {
    private PaymentSettlementDomainConstants() {
    }

    /**
     * Free entry time for all schools
     */
    public static final LocalTime FREE_SCHOOL_ENTRY_TIME = LocalTime.of(7, 0);

    /**
     * Free exit time for all schools
     */
    public static final LocalTime FREE_SCHOOL_EXIT_TIME = LocalTime.of(12, 0);

    /**
     * Seconds in one hour
     */
    public static final int SECONDS_IN_HOUR = 3600;
}
