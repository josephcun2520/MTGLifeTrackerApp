package com.example.mtglifetrackerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.mtglifetrackerapp.HealthFragment
import com.example.mtglifetrackerapp.DiceActivity
import com.example.mtglifetrackerapp.databinding.ActivityMainBinding
import java.util.*

private lateinit var binding : ActivityMainBinding
private var players = Vector<PlayerData>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val text = "Hello toast!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
        //TODO: use putextra to save state between activity transitions

        players.addAll(listOf(PlayerData(1, 20, false), PlayerData(2, 20, false), PlayerData(3,20, false), PlayerData(4,20, false)))
        //TODO: make id unique

        val but : Button = binding.diceButton
        but.setOnClickListener {
            val intent = Intent (this@MainActivity, DiceActivity::class.java)
            startActivity(intent)
        }

        @Suppress("RedundantIf")
        fun selectMonarch(idx : Int) {                  //0 indexed
            for (i in players.indices) {
                if (i == idx) {
                    players[i].isMonarch = true
                }
                else {
                    players[i].isMonarch = false
                }
            }
        }

        //TODO: use put extra to save state between activity transitions
    }
}