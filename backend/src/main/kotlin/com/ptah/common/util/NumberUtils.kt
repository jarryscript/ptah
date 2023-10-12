package com.ptah.common.util

import java.math.BigDecimal
import java.util.*

    fun isGreaterThan(numberA: BigDecimal?, numberB: BigDecimal?): Boolean {
        Objects.requireNonNull(numberA)
        Objects.requireNonNull(numberB)
        return numberA?.compareTo(numberB) == 1
    }

    fun isLessThan(numberA: BigDecimal, numberB: BigDecimal): Boolean {
        Objects.requireNonNull(numberA)
        Objects.requireNonNull(numberB)
        return numberA.compareTo(numberB) == -1
    }

    fun isGreaterThanOrEqualsTo(numberA: BigDecimal?, numberB: BigDecimal?): Boolean =
        isGreaterThan(numberA, numberB) || numberA == numberB
