package com.school.payment.system.payment.settlement.service.domain.entity;

import com.school.payment.system.domain.entity.AggregateRoot;
import com.school.payment.system.payment.settlement.service.domain.valueobject.AttendanceTimeId;

import java.time.*;

/**
 * Has year and month and calculate beginning and end of month based on that
 */
public class AttendanceTime extends AggregateRoot<AttendanceTimeId> {
    private final Year year;
    private final Month month;
    private LocalDateTime minDate;
    private LocalDateTime maxDate;

    public AttendanceTime(Year year, Month month) {
        this.year = year;
        this.month = month;
    }

    /**
     * calculates first and last DateTime of month
     */
    public void calculateMinAndMaxDate() {
        YearMonth yearMonth = YearMonth.of(year.getValue(), month.getValue());
        minDate = LocalDateTime.of(yearMonth.atDay(1), LocalTime.MIN);
        maxDate = LocalDateTime.of(yearMonth.atEndOfMonth(), LocalTime.MAX);
    }

    public Year getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    public LocalDateTime getMinDate() {
        return minDate;
    }

    public LocalDateTime getMaxDate() {
        return maxDate;
    }
}
