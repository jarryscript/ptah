package com.ptah.common.util;

import java.math.BigDecimal;
import java.util.Objects;

public final class NumberUtils {

    public static boolean isGreaterThan(BigDecimal numberA, BigDecimal numberB){
        Objects.requireNonNull(numberA);
        Objects.requireNonNull(numberB);
        return numberA.compareTo(numberB) ==1;
    }

    public static boolean isLessThan(BigDecimal numberA, BigDecimal numberB){
        Objects.requireNonNull(numberA);
        Objects.requireNonNull(numberB);
        return numberA.compareTo(numberB) ==-1;
    }

    public static boolean isGreaterThanOrEqualsTo(BigDecimal numberA, BigDecimal numberB){
        return isGreaterThan(numberA,numberB) || numberA.equals(numberB);
    }
}
