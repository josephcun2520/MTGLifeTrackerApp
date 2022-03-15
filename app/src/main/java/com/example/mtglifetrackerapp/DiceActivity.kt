package com.example.mtglifetrackerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mtglifetrackerapp.databinding.ActivityDiceBinding

class DiceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDiceBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }


}