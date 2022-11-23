package com.example.simplecalc

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * SimpleCalc is the initial version of SimpleCalcTest.  It has
 * a number of intentional oversights for the student to debug/fix,
 * including input validation (no input, bad number format, div by zero)
 *
 * In addition there is only one (simple) unit test in this app.
 * All the input validation and the unit tests are added as part of the lessons.
 *
 */
class MainActivity : AppCompatActivity() {

    private var mCalculator: Calculator? = null
    private var mOperandOneEditText: EditText? = null
    private var mOperandTwoEditText: EditText? = null
    private var mResultTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the calculator class and all the views
        mCalculator = Calculator()
        mResultTextView = findViewById(R.id.operation_result_text_view)
        mOperandOneEditText = findViewById(R.id.operand_one_edit_text)
        mOperandTwoEditText = findViewById(R.id.operand_two_edit_text)
    }

    /**
     * OnClick method called when the add Button is pressed.
     */
    fun onAdd(view: View?) {
        compute(Calculator.Operator.ADD)
    }

    /**
     * OnClick method called when the subtract Button is pressed.
     */
    fun onSub(view: View?) {
        compute(Calculator.Operator.SUB)
    }
    /**
     * OnClick method called when the power Button is pressed.
     */
    fun onPow(view: View?) {
        compute(Calculator.Operator.POW)
    }

    /**
     * OnClick method called when the divide Button is pressed.
     */
    fun onDiv(view: View?) {
        try {
            compute(Calculator.Operator.DIV)
        } catch (iae: IllegalArgumentException) {
            Log.e(TAG, "IllegalArgumentException", iae)
            mResultTextView?.text = getString(R.string.computationError)
        }
    }

    /**
     * OnClick method called when the multiply Button is pressed.
     */
    fun onMul(view: View?) {
        compute(Calculator.Operator.MUL)
    }

    private fun compute(operator: Calculator.Operator) {
        val operandOne: Double
        val operandTwo: Double
        try {
            operandOne = getOperand(mOperandOneEditText)
            operandTwo = getOperand(mOperandTwoEditText)
        } catch (nfe: NumberFormatException) {
            Log.e(TAG, "NumberFormatException", nfe)
            mResultTextView?.text = getString(R.string.computationError)
            return
        }
        val result: String
        when (operator) {
            Calculator.Operator.ADD -> result =
                mCalculator?.add(operandOne, operandTwo).toString()

            Calculator.Operator.SUB -> result =
                mCalculator?.sub(operandOne, operandTwo).toString()

            Calculator.Operator.DIV -> result =
                mCalculator?.div(operandOne, operandTwo).toString()

            Calculator.Operator.MUL -> result =
                mCalculator?.mul(operandOne, operandTwo).toString()

            Calculator.Operator.POW -> result =
                mCalculator?.pow(operandOne, operandTwo).toString()
        }
        mResultTextView?.text = result
    }

    companion object {
        private const val TAG = "CalculatorActivity"

        /**
         * @return the operand value entered in an EditText as double.
         */
        private fun getOperand(operandEditText: EditText?): Double {
            val operandText = getOperandText(operandEditText)
            return operandText.toDouble()
        }

        /**
         * @return the operand text which was entered in an EditText.
         */
        private fun getOperandText(operandEditText: EditText?): String {
            return operandEditText?.text.toString()
        }
    }
}