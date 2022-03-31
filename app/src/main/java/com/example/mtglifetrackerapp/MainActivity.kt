package com.example.mtglifetrackerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
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

//lateinit var notificationManager: NotificationManager
//notificationManager = requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager

//    private fun shareResults(shareText : String) {
//        val sendIntent : Intent = Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(Intent.EXTRA_TEXT, shareText)
//            type = "text/plain"
//        }
//
//        val shareIntent = Intent.createChooser(sendIntent, "Share Via ")
//        startActivity(shareIntent)
//    }
//