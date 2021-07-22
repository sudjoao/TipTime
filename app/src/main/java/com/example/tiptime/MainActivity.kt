package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip(){
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        if(cost == null){
            binding.tipAmount.text = ""
            return
        }
        val tipPercent = when(binding.tipOptions.checkedRadioButtonId){
            R.id.amazing_service -> 0.2
            R.id.good_service -> 0.18
            else -> 0.15
        }
        var tip = tipPercent * cost
        val roundUp = binding.roundSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount_text, formattedTip)
    }
}