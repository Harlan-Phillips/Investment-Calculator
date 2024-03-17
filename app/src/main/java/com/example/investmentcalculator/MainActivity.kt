package com.example.investmentcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var editTextNumberDecimal: EditText
    private lateinit var durationSeekBar: SeekBar
    private lateinit var durationTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var calculateButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal)
        durationSeekBar = findViewById(R.id.seekBar)
        durationTextView = findViewById(R.id.duration)
        resultTextView = findViewById(R.id.money)
        calculateButton = findViewById(R.id.button)


        durationSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // range of display values 1-50 years
                    val adjustedProgress = (progress / 2) + 1
                    val yearString = "$adjustedProgress years"

                    durationTextView.text = yearString
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        calculateButton.setOnClickListener {

            val targetAmount = editTextNumberDecimal.text.toString().toDoubleOrNull() ?: 0.0

            val years = durationSeekBar.progress / 2 + 1
            val n = years * 12

            val annualRate = 0.08
            val r = annualRate / 12


            val numerator = Math.pow(1 + r, n.toDouble()) - 1
            val monthlyInvestment = targetAmount * r / numerator

            var formattedMonthlyInvestment = String.format("%.2f", monthlyInvestment)


            resultTextView.text = formattedMonthlyInvestment
        }
   }





}