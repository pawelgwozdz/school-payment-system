package com.school.payment.system.payment.settlement.service.dataaccess.child.attendance.exception;

/**
 * Exception class for handling db exceptions
 */
public class ChildAttendanceDataAccessException extends RuntimeException {
    public ChildAttendanceDataAccessException(String message) {
        super(message);
    }
}
