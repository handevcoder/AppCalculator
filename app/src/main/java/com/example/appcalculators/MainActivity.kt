package com.example.appcalculators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnZero.setOnClickListener { appendVal("0", false) }
        btnOne.setOnClickListener { appendVal("1", false) }
        btnTwo.setOnClickListener { appendVal("2", false) }
        btnThree.setOnClickListener { appendVal("3", false) }
        btnFour.setOnClickListener { appendVal("4", false) }
        btnFive.setOnClickListener { appendVal("5", false) }
        btnSix.setOnClickListener { appendVal("6", false) }
        btnSeven.setOnClickListener { appendVal("7", false) }
        btnEight.setOnClickListener { appendVal("8", false) }
        btnNine.setOnClickListener { appendVal("9", false) }
        btnZerozero.setOnClickListener { appendVal("00", false) }

        // Operators
        btnPlus.setOnClickListener { appendVal("+", false) }
        btnMinus.setOnClickListener { appendVal("-", false) }
        btnMultiply.setOnClickListener { appendVal("*", false) }
        btnDivide.setOnClickListener { appendVal("/", false) }
        btnZerozero.setOnClickListener { appendVal("00", false) }
        btnMod.setOnClickListener { appendVal("%", false) }
        btnDot.setOnClickListener { appendVal(".", false) }

        btnDelete.setOnClickListener {
            val expression = callExpression.text.toString()
            if (expression.isNotEmpty()) {
                callExpression.text = expression.substring(0, expression.length - 1)
            }
        }

        btnC.setOnClickListener {
            callExpression.text = ""
            callResult.text = ""
        }

        btnEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(callExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {

                    callResult.text = longResult.toString()
                } else
                    callResult.text = result.toString()

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                Log.d("EXCEPTION", "Message: ${e.message}")
            }
        }
    }

    private fun appendVal(string: String, isClear: Boolean) {
        if (isClear) {
            callExpression.text = ""
            callExpression.append(string)
        } else {
            callExpression.append(string)
        }
    }
}