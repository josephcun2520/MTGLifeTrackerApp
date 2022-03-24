package com.example.mtglifetrackerapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.mtglifetrackerapp.databinding.ActivityMainBinding
import java.util.*

private lateinit var binding : ActivityMainBinding

private var players = Vector<PlayerData>()
var pattern = longArrayOf(0, 250, 500, 250, 500)
var winPattern = longArrayOf(0, 500, 250, 500, 250)
private lateinit var winner : PlayerData
lateinit var notificationManager: NotificationManager
var gameOver = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val v = getSystemService(VIBRATOR_SERVICE) as Vibrator

        players.addAll(listOf(PlayerData(1, 20, false, 0, 0, 0, 0),
            PlayerData(2, 20, false, 0, 0, 0, 0),
            PlayerData(3,20, false, 0, 0, 0, 0),
            PlayerData(4,20, false, 0, 0, 0, 0)))
        //TODO: make id unique / String or use database?

        //TODO: connect the buttons up to the data class

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

        fun winnerNotify() {
            val channel_ID = "MTG"
            val pendingIntent = getActivity(this, 0, intent, FLAG_UPDATE_CURRENT)

            var builder = NotificationCompat.Builder(this, channel_ID)
                //.setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Game Over")
                .setContentText("Winner is Player " + winner.id)
                //.setStyle(NotificationCompat.BigTextStyle()
                    //.bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)        //TODO: test intent

                //Send notification
            notificationManager.notify(1, builder.build())
            //TODO: add button to share data to other apps / enable pre-existing one
        }

        fun shareResults() {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Winner: Player " + winner.id + "\nHealth: " + winner.health)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        fun onLoseHealth(playerIdx : Int, vib : Vibrator) {

            gameOver = false
            var aliveCount = 0
            for (i in players.indices) {
                if (players[i].health > 0) {
                    aliveCount++
                    winner = players[i] //If only one alive then winner is correct
                }
            }

            if (aliveCount <= 1) {
                winnerNotify()
                vib.vibrate(winPattern, -1) //Win pattern
                //TODO: disable all buttons & enable the share button / screen
            } else {
                if (players[playerIdx].health - 1 == 0) {    //Dead
                    //TODO: disable the button here
                    --players[playerIdx].health
                    vib.vibrate(pattern, -1)       //Player dead
                } else {
                    --players[playerIdx].health //Decrement health
                    vib.vibrate(500)
                }
            }
        }

        //TODO: use put extra to save state between activity transitions
    }
}