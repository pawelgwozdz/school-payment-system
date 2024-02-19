package com.school.payment.system.domain.function;

/**
 * Functional interface with 3 parameters in and 1 parameter out
 * @param <A> First parameter in
 * @param <B> Second parameter in
 * @param <C> Third parameter in
 * @param <R> First parameter out (Result)
 */
@FunctionalInterface
public interface Function3 <A, B, C, R>{

    R apply(A a, B b, C c);
}
