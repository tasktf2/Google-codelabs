package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test

class TipCalculatorTests {

    @Test
    fun `calculate 20 percent tip no round up`() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = "$2.00"
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }
}