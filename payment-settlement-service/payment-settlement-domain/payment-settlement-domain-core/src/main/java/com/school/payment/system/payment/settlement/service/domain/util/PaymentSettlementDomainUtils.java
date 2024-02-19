package com.school.payment.system.payment.settlement.service.domain.util;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Util class for payment settlement domain
 */
public class PaymentSettlementDomainUtils {

    private PaymentSettlementDomainUtils() {

    }

    /**
     * returns duration if positive between 2 durations
     * @param end Time ending duration
     * @param start Time starting duration
     * @return if positive returns duration, if negative returns duration of zero
     */
    public static Duration getDurationIfPositive(LocalTime end, LocalTime start) {
        if (end.compareTo(start) > 0) {
            return Duration.ZERO;
        } else {
            return Duration.between(end, start);
        }
    }

    /**
     * Round up double value to the nearest int, for example 1.1 to 2, 6.7 to 7, 20.01 to 21
     * @param value value to round up
     * @return rounded up value
     */
    public static int roundUpToNearestInt(double value) {
        return (int) Math.ceil(value);
    }
}
