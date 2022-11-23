package com.example.simplecalc

import kotlin.math.pow

class Calculator {
    // Available operations
    enum class Operator {
        ADD, SUB, DIV, MUL, POW
    }

    /**
     * Addition operation
     */
    fun add(firstOperand: Double, secondOperand: Double): Double {
        return firstOperand + secondOperand
    }

    /**
     * Subtract operation
     */
    fun sub(firstOperand: Double, secondOperand: Double): Double {
        return firstOperand - secondOperand
    }

    /**
     * Divide operation
     */
    fun div(firstOperand: Double, secondOperand: Double): Double {
        return firstOperand / secondOperand
    }

    /**
     * Multiply operation
     */
    fun mul(firstOperand: Double, secondOperand: Double): Double {
        return firstOperand * secondOperand
    }

    /**
     * Power operation
     */
    fun pow(firstOperand: Double, secondOperand: Double): Double {
        return firstOperand.pow(secondOperand)
    }
}