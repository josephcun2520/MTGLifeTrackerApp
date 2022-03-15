package com.example.mtglifetrackerapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.mtglifetrackerapp.databinding.ActivityDiceBinding

class DiceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDiceBinding

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




        //roll different types of dice + quantity


    }


}