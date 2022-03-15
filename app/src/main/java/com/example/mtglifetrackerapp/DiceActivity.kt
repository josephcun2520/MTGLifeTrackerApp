package com.example.mtglifetrackerapp

import android.app.Activity
import android.os.Bundle
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
        but.setOnClickListener { submit() }
        //roll different types of dice + quantity
    }

    private fun submit() {
        //if wrong input (0 / too many dice) on edittext then reset values and don't perform submit
        val amountStr = binding.quantity.text.toString()
        val amount : Int = Integer.parseInt(amountStr)
        val results = binding.results
        val spin = binding.diceSpinner

        if (amount > 4) {   //Too many

        } else {        //Do roll/spins
            //get answer from spinner
            //animated roll/flip??
            //call different functions in for loop depending on quantity
            //display results in the results textview
        }
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
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

}