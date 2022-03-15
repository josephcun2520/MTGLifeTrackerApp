package com.example.mtglifetrackerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.mtglifetrackerapp.DiceActivity
import com.example.mtglifetrackerapp.databinding.ActivityMainBinding

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val but : Button = binding.diceButton
        but.setOnClickListener {
            val intent = Intent (this@MainActivity, DiceActivity::class.java)
            startActivity(intent)
        }


        //TODO: use put extra to save state between activity transitions
    }
}