package com.example.madt_task5

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyTest {
    @Test
    fun testCurrency() {
        val currency = Currency("USD", 1.0)
        assertEquals("USD", currency.name)
        assertEquals(1.0, currency.rate, 0.0)
    }
}