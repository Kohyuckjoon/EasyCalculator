package com.example.chapter5

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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

    fun eqaulsClicked(view: View) {

    }

    fun updateEquationTextView() {
        val firstformattedNubes = if(firstNumberText.isNotEmpty()) decimalFormat.format(firstNumberText.toString().toBigDecimal()) else ""
        val secondformattedNubes = if(secondNumberText.isNotEmpty()) decimalFormat.format(firstNumberText.toString().toBigDecimal()) else ""
        databinding.calculation.text = "$firstformattedNubes $operatorText $secondformattedNubes"
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
            
            databinding.btPlus.id -> numberButtonClicked("+")
            databinding.btMinus.id -> numberButtonClicked("-")
            databinding.btResult.id -> numberButtonClicked("=")
        }
    }

    fun numberButtonClicked(number: String) {

    }
}