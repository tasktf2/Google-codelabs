package com.example.simplecalc

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class CalculatorTest {

    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun `add two positive numbers`() {
        val num1 = 1.0
        val num2 = 2.0
        val expected = 3.0
        val result = calculator.add(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `add two negative numbers`() {
        val num1 = -1.0
        val num2 = -2.0
        val expected = -3.0
        val result = calculator.add(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `add negative to positive number`() {
        val num1 = 1.0
        val num2 = -2.0
        val expected = -1.0
        val result = calculator.add(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `add float to double`() {
        val num1 = 1.111F.toDouble()
        val num2 = 1.111
        val expected = 2.222
        val result = calculator.add(num1, num2)

        assertEquals(expected, result, 0.001)
    }

    @Test
    fun `sub two numbers`() {
        val num1 = 1.0
        val num2 = 1.0
        val expected = 0.0
        val result = calculator.sub(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `sub works with negative results`() {
        val num1 = 5.0
        val num2 = 10.0
        val expected = -5.0
        val result = calculator.sub(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `mul two numbers`() {
        val num1 = 5.0
        val num2 = 10.0
        val expected = 50.0
        val result = calculator.mul(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `mul two numbers where one is zero`() {
        val num1 = 5.0
        val num2 = 0.0
        val expected = 0.0
        val result = calculator.mul(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `div two numbers`() {
        val num1 = 5.1
        val num2 = 1.7
        val expected = 3.0
        val result = calculator.div(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `div two numbers where divider is zero`() {
        val num1 = 5.1
        val num2 = 0.0
        val expected = Double.POSITIVE_INFINITY
        val result = calculator.div(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `div two numbers where divider is zero (negative dividend)`() {
        val num1 = -5.1
        val num2 = 0.0
        val expected = Double.NEGATIVE_INFINITY
        val result = calculator.div(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `div two numbers where divider is infinity`() {
        val num1 = 5.1
        val num2 = Double.POSITIVE_INFINITY
        val expected = 0.0
        val result = calculator.div(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `pow 1st is negative`() {
        val num1 = -7.0
        val num2 = 2.0
        val expected = 49.0
        val result = calculator.pow(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `pow 2nd is negative`() {
        val num1 = 25.0
        val num2 = -2.0
        val expected = 0.0016
        val result = calculator.pow(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `pow 1st is zero`() {
        val num1 = 0.0
        val num2 = 2.0
        val expected = 0.0
        val result = calculator.pow(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `pow 2nd is zero`() {
        val num1 = 16.0
        val num2 = 0.0
        val expected = 1.0
        val result = calculator.pow(num1, num2)

        assertEquals(expected, result, 0.0)
    }

    @Test
    fun `pow zero to negative`() {
        val num1 = 0.0
        val num2 = -5.0
        val expected = Double.POSITIVE_INFINITY
        val result = calculator.pow(num1, num2)

        assertEquals(expected, result, 0.0)
    }
}