package com.example.chapter5

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter5.databinding.ActivityMyCalculateBinding
import java.text.DecimalFormat

class MyCalculate : AppCompatActivity() {

    private lateinit var databinding: ActivityMyCalculateBinding
    private val decimalFormat = DecimalFormat("#,###")
    private val firstNumberText = StringBuilder("")
    private val secondNumberText = StringBuilder("")
    private val operatorText = StringBuilder("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = ActivityMyCalculateBinding.inflate(layoutInflater)
        databinding.calculationResult.text = ""

        setContentView(databinding.root)
    }

    fun clearButtonClicked(view: View) {
        Log.e("khj", "view : " + view)

        firstNumberText.clear()
        secondNumberText.clear()
        operatorText.clear()
        updateEquationTextView()

        databinding.calculation.text = ""
        databinding.calculationResult.text = ""
    }

    fun numberClicked(view: View) {
        Log.e("khj ---> ", "view : " + view)
        val numberString = (view as? Button)?.text?.toString()?: ""
        val numberText = if(operatorText.isEmpty()) firstNumberText else secondNumberText

        numberText.append(numberString)
        buttonClick(view)
        updateEquationTextView()
    }

    fun myOperatorClicked(view: View) {
        Log.e("khj ---> ", "clear")
        val operatorString = (view as? Button)?.text?.toString()?: ""

        if (firstNumberText.isEmpty()) {
            Toast.makeText(this, "먼저 숫자를 입력하세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (secondNumberText.isNotEmpty()) {
            Toast.makeText(this, "1개의 연산자에 대해서만 연산이 가능합니다..", Toast.LENGTH_SHORT).show()
            return
        }

        operatorText.append(operatorString)
        updateEquationTextView()
    }

    fun updateEquationTextView() {
        val firstformattedNumber = if(firstNumberText.isNotEmpty()) decimalFormat.format(firstNumberText.toString().toBigDecimal()) else ""
        val secondformattedNumber = if(secondNumberText.isNotEmpty()) decimalFormat.format(secondNumberText.toString().toBigDecimal()) else ""
        databinding.calculation.text = "$firstformattedNumber $operatorText $secondformattedNumber"
    }

    fun buttonClick(view: View) {
        when (view.id) {
            databinding.bt0st.id -> numberButtonClicked("0")
            databinding.bt1st.id -> numberButtonClicked("1")
            databinding.bt2st.id -> numberButtonClicked("2")
            databinding.bt3st.id -> numberButtonClicked("3")
            databinding.bt4st.id -> numberButtonClicked("4")
            databinding.bt5st.id -> numberButtonClicked("5")
            databinding.bt6st.id -> numberButtonClicked("6")
            databinding.bt7st.id -> numberButtonClicked("7")
            databinding.bt8st.id -> numberButtonClicked("8")
            databinding.bt9st.id -> numberButtonClicked("9")
        }
    }

    fun numberButtonClicked(number: String) {

    }

    fun resultClicked (view: View) {
        if (firstNumberText.isEmpty() || secondNumberText.isEmpty() || operatorText.isEmpty()) {
            Toast.makeText(this, "올바르지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val firstNumber = firstNumberText.toString().toBigDecimal()
        val secondNumber = secondNumberText.toString().toBigDecimal()

        val result =
            when (operatorText.toString()) {
                "+" -> decimalFormat.format(firstNumber + secondNumber)
                "-" -> decimalFormat.format(firstNumber - secondNumber)

                else -> {
                    "잘못된 수식"
                }
            }.toString()
        Log.e("khj", "result ---> " + result)

        databinding.calculationResult.text = result
    }
}