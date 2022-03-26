package com.example.mtglifetrackerapp

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.mtglifetrackerapp.databinding.ActivityDiceBinding


class DiceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDiceBinding
    private lateinit var sA : SpinnerActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDiceBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val v = getSystemService(VIBRATOR_SERVICE) as Vibrator

        val spinner : Spinner = binding.diceSpinner
        //Create array adapter from string array
        ArrayAdapter.createFromResource(
            this,
            R.array.dice_array,
            android.R.layout.simple_spinner_item).also {
                adapter ->
            //Specify layout to use for list
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //Apply adapter to spinner
            spinner.adapter = adapter
        }

        val but : Button = binding.Submit
        but.setOnClickListener {
            submit()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                //deprecated in API 26
                v.vibrate(500)                  //TODO: Test
            }
        }
        //roll different types of dice + quantity
    }

    private fun submit() {
        //if wrong input (0 / too many dice) on edittext then reset values and don't perform submit
        val amountStr = binding.quantity
        val amount : Int = Integer.parseInt(amountStr.text.toString())
        val results = binding.results
        val spin = binding.diceSpinner
        var resultStr = ""

        if (amount <= 0 || spin.selectedItem.toString() == "Select Dice") {   //not enough quantity or default spinner
            results.text = ""           //Reset text and spinner
            amountStr.setText(0)
            spin.setSelection(0)    //Set to default position
        } else {        //Do roll/spins
            val spinVal : String = spin.selectedItem.toString()
            //For quantity, roll the right dice
            for (i in 0..amount) {
                when (spinVal) {
                    "Coinflip" -> resultStr += "Coin Flip Result " + coinFlip() + "\n"
                    "D6" -> resultStr += "Dice Result " + rollDx(6) + "\n"
                    "D20" -> resultStr += "Dice Result " + rollDx(20) + "\n"
                    else -> { // Note the block
                        print("ERROR")
                    }
                }
            }
        }
        //display results in the results textview
        results.text = resultStr
    }

    private fun rollDx(x : Int) : Int {
        return (1..x).shuffled().first()
    }

    private fun coinFlip() : String {
        return listOf("Heads", "Tails").random()
    }

}


class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

}