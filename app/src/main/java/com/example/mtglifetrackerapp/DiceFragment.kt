package com.example.mtglifetrackerapp

import android.app.Activity
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.DialogFragment
import com.example.mtglifetrackerapp.databinding.FragmentDiceBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class DiceFragment : DialogFragment() {

    //private lateinit var binding : FragmentDiceBinding
    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val v = requireContext().getSystemService(VIBRATOR_SERVICE) as Vibrator

        val spinner : Spinner = binding.diceSpinner
        //Create array adapter from string array
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dice_array,
            android.R.layout.simple_spinner_item).also {
                adapter ->
            //Specify layout to use for list
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //Apply adapter to spinner
            spinner.adapter = adapter
        }

        binding.Submit.setOnClickListener {
            submit()
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        }
        //roll different types of dice + quantity

        binding.closeButton.setOnClickListener { dismiss() }
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {       //Make box fit screen size
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog!!.window!!.setLayout(width, height)
        }
    }


    private fun submit() {
        //if wrong input (0 / too many dice) on edittext then reset values and don't perform submit
        val amountStr = binding.quantity
        var amount : Int = Integer.parseInt(amountStr.text.toString())
        if (amount < 1)
        {
            amount = 1
        }
        val results = binding.results
        val spin = binding.diceSpinner
        var resultStr = ""

        if (amount <= 0 || spin.selectedItem.toString() == "Select Dice") {   //not enough quantity or default spinner
            results.text = ""           //Reset text and spinner
            amountStr.setText(0)
            spin.setSelection(0)    //Set to default position
        } else {        //Do roll/spins
            val spinVal : String = "D6"//spin.selectedItem.toString()
            //For quantity, roll the right dice
            for (i in 1..amount) {
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

    class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
            // An item was selected. You can retrieve the selected item using
            parent.getItemAtPosition(pos)
        }

        override fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback
        }

    }

}