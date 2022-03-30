package com.example.mtglifetrackerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

var diceButton : ImageButton? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceButton = findViewById(R.id.diceButton)
        diceButton?.setOnClickListener {openDiceScreen()}
    }

    private fun openDiceScreen(){
        val newFragment = DiceFragment()
        newFragment.show(supportFragmentManager, "game")
    }
}