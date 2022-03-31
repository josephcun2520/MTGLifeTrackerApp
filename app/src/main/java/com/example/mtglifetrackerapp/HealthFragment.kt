package com.example.mtglifetrackerapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.os.Bundle
import android.os.VibrationEffect
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.*
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.math.abs

class HealthFragment : Fragment() {
    private var healthCount : TextView? = null
    private var commanderDmg1 : TextView? = null
    private var commanderDmg2 : TextView? = null
    private var commanderDmg3: TextView? = null
    private var cDmgUp1Btn : ImageButton? = null
    private var cDmgUp2Btn : ImageButton? = null
    private var cDmgUp3Btn : ImageButton? = null
    private var cDmgDown1Btn : ImageButton? = null
    private var cDmgDown2Btn : ImageButton? = null
    private var cDmgDown3Btn : ImageButton? = null
    private var heartImg : ImageView? = null
    private var edhImg1 : ImageView? = null
    private var edhImg2 : ImageView? = null
    private var edhImg3 : ImageView? = null
    private var upButton : ImageButton? = null
    private var downButton : ImageButton? = null
    private var bloodImg : ImageView? = null
    private var energyImg : ImageView? = null
    private var poisonImg : ImageView? = null
    private var token1 : TextView? = null
    private var token2 : TextView? = null
    private var token3 : TextView? = null
    private var token1UpBtn : ImageButton? = null
    private var token2UpBtn : ImageButton? = null
    private var token3UpBtn : ImageButton? = null
    private var token1DownBtn : ImageButton? = null
    private var token2DownBtn : ImageButton? = null
    private var token3DownBtn : ImageButton? = null
    private var bloodTokens = 0
    private var energyTokens = 0
    private var poisonTokens = 0
    private var leftButton : ImageButton? = null
    private var rightButton : ImageButton? = null
    private var health : Int = 40
    private var cDmg1 : Int = 0
    private var cDmg2 : Int = 0
    private var cDmg3 : Int = 0
    private var page : Int = 1
    private var playNo = 0
    private var c1 : Int = 0
    private var c2 : Int = 0
    private var c3 : Int = 0

    //Variables
    private lateinit var vib : Vibrator
    var deathPattern = longArrayOf(0, 250, 0, 250)
    private var players = Vector<PlayerData>()
    var gameOver = false
    var deaths = arrayOf(false, false, false, false)   //To track who is dead
    lateinit var notificationManager: NotificationManager
    //lateinit var shareBut : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationManager = requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        vib =  requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        createNotificationChannel("MTG", "MTG-Lifetracker", "Description" )
    }

    private fun createNotificationChannel(id: String, name: String, desc: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val notChannel = NotificationChannel(id, name, importance)
        notChannel.description = desc
        notChannel.enableLights(true)
        notChannel.lightColor = Color.BLUE

        notificationManager.createNotificationChannel(notChannel)
    }

    private fun playerNotify(notifyText : String) {
        val channelID = "MTG"
        val intent = Intent(requireContext(), MainActivity::class.java). apply {
            flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT
            //flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent : PendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var builder = NotificationCompat.Builder(requireContext(), channelID)
            .setSmallIcon(R.drawable.monarch_icon)
            .setContentTitle("MTG Tracker")
            .setContentText(notifyText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(1, builder.build())

    }

    fun changeHealth(amount:Int): View.OnClickListener? {
        var text = ""

        if (health <= 0) {  //Dead
            health = 0
            healthCount?.setText((health.toString()))
            vib.vibrate(VibrationEffect.createWaveform(deathPattern, -1))
            playerNotify("Player $playNo has died")
            //TODO: notify of death here or in commander damage or poison damage
        } else {
            health += amount
            healthCount?.setText(health.toString())

            if (amount < 0) {
                vib.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
                val posAmount = amount * -1
                text = "Player $playNo lost $posAmount health!"
            } else {
                text = "Player $playNo gained $amount health!"
            }
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
        checkAlive()
        return null
    }

    fun checkAlive() {
        var deaths = arrayOf(false, false, false, false)   //To track who is dead
        var deathCount = 0
        var aliveIdx = -1

        for (i in 0..4) {
            if (playNo == i && health <= 0)   {  //If current player is dead
                deaths[i] = true
                deathCount++
            }
        }
        if (deathCount == 3) { //only one player left
            aliveIdx = deaths.indexOf(true)
        }

        //if (aliveIdx != -1) {
            //shareResults("Winner is Player $aliveIdx+1")
        //}
    }

    private fun shareResults(shareText : String) {
        val sendIntent : Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        //val shareIntent = Intent.createChooser(sendIntent, "Share Via ")
        startActivity(Intent.createChooser(sendIntent, "Share Via "))
    }

    private fun changeCommanderDamage(amount:Int, player:Int):View.OnClickListener? {
        when (player) {
            1 -> {
                cDmg1 += amount
                commanderDmg1?.text = cDmg1.toString()
                if (cDmg1 >= 21)
                {
                    vib.vibrate(VibrationEffect.createWaveform(deathPattern, -1))
                    val text = "Player $c1 dies to commander damage!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }
            }
            2 -> {
                cDmg2 += amount
                commanderDmg2?.text = cDmg2.toString()
                if (cDmg2 >= 21)
                {
                    vib.vibrate(VibrationEffect.createWaveform(deathPattern, -1))
                    val text = "Player $c2 dies to commander damage!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }
            }
            3 -> {
                cDmg3 += amount
                commanderDmg3?.text = cDmg3.toString()
                if (cDmg3 >= 21)
                {
                    vib.vibrate(VibrationEffect.createWaveform(deathPattern, -1))
                    val text = "Player $c3 dies to commander damage!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }
            }
        }
        return null
    }

    private fun changeTokens(amount:Int, type:Int):View.OnClickListener? {
        if (type == 1){
            bloodTokens += amount
            token1?.text = bloodTokens.toString()
        }
        else if (type == 2) {
            energyTokens += amount
            token2?.text = energyTokens.toString()
        }
        else if (type == 3) {
            poisonTokens += amount
            token3?.text = poisonTokens.toString()

            if (poisonTokens >= 10 && health > 0)
            {
                vib.vibrate(VibrationEffect.createWaveform(deathPattern, -1))
                health = 0
                healthCount?.text = health.toString()
                val text = "Player $playNo dies to poison!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        }

        return null
    }

    private fun changePage(amount:Int): View.OnClickListener? {
        page += amount

        if (page > 2)
            page = 2
        else if (page < 0)
            page = 0

        when (page) {
            0 -> {
                healthCount?.visibility=View.INVISIBLE
                upButton?.visibility=View.INVISIBLE
                downButton?.visibility=View.INVISIBLE
                heartImg?.visibility=View.INVISIBLE

                commanderDmg1?.visibility=View.VISIBLE
                commanderDmg2?.visibility=View.VISIBLE
                commanderDmg3?.visibility=View.VISIBLE
                cDmgUp1Btn?.visibility=View.VISIBLE
                cDmgUp2Btn?.visibility=View.VISIBLE
                cDmgUp3Btn?.visibility=View.VISIBLE
                cDmgDown1Btn?.visibility=View.VISIBLE
                cDmgDown2Btn?.visibility=View.VISIBLE
                cDmgDown3Btn?.visibility=View.VISIBLE
                edhImg1?.visibility=View.VISIBLE
                edhImg2?.visibility=View.VISIBLE
                edhImg3?.visibility=View.VISIBLE

                token1?.visibility=View.INVISIBLE
                token2?.visibility=View.INVISIBLE
                token3?.visibility=View.INVISIBLE
                token1UpBtn?.visibility=View.INVISIBLE
                token2UpBtn?.visibility=View.INVISIBLE
                token3UpBtn?.visibility=View.INVISIBLE
                token1DownBtn?.visibility=View.INVISIBLE
                token2DownBtn?.visibility=View.INVISIBLE
                token3DownBtn?.visibility=View.INVISIBLE
                bloodImg?.visibility=View.INVISIBLE
                energyImg?.visibility=View.INVISIBLE
                poisonImg?.visibility=View.INVISIBLE

                leftButton?.visibility=View.INVISIBLE
            }
            1 -> {
                healthCount?.visibility=View.VISIBLE
                upButton?.visibility=View.VISIBLE
                downButton?.visibility=View.VISIBLE
                heartImg?.visibility=View.VISIBLE

                commanderDmg1?.visibility=View.INVISIBLE
                commanderDmg2?.visibility=View.INVISIBLE
                commanderDmg3?.visibility=View.INVISIBLE
                cDmgUp1Btn?.visibility=View.INVISIBLE
                cDmgUp2Btn?.visibility=View.INVISIBLE
                cDmgUp3Btn?.visibility=View.INVISIBLE
                cDmgDown1Btn?.visibility=View.INVISIBLE
                cDmgDown2Btn?.visibility=View.INVISIBLE
                cDmgDown3Btn?.visibility=View.INVISIBLE
                edhImg1?.visibility=View.INVISIBLE
                edhImg2?.visibility=View.INVISIBLE
                edhImg3?.visibility=View.INVISIBLE

                token1?.visibility=View.INVISIBLE
                token2?.visibility=View.INVISIBLE
                token3?.visibility=View.INVISIBLE
                token1UpBtn?.visibility=View.INVISIBLE
                token2UpBtn?.visibility=View.INVISIBLE
                token3UpBtn?.visibility=View.INVISIBLE
                token1DownBtn?.visibility=View.INVISIBLE
                token2DownBtn?.visibility=View.INVISIBLE
                token3DownBtn?.visibility=View.INVISIBLE
                bloodImg?.visibility=View.INVISIBLE
                energyImg?.visibility=View.INVISIBLE
                poisonImg?.visibility=View.INVISIBLE

                leftButton?.visibility=View.VISIBLE
                rightButton?.visibility=View.VISIBLE
            }
            2 -> {
                healthCount?.visibility=View.INVISIBLE
                upButton?.visibility=View.INVISIBLE
                downButton?.visibility=View.INVISIBLE
                heartImg?.visibility=View.INVISIBLE

                commanderDmg1?.visibility=View.INVISIBLE
                commanderDmg2?.visibility=View.INVISIBLE
                commanderDmg3?.visibility=View.INVISIBLE
                cDmgUp1Btn?.visibility=View.INVISIBLE
                cDmgUp2Btn?.visibility=View.INVISIBLE
                cDmgUp3Btn?.visibility=View.INVISIBLE
                cDmgDown1Btn?.visibility=View.INVISIBLE
                cDmgDown2Btn?.visibility=View.INVISIBLE
                cDmgDown3Btn?.visibility=View.INVISIBLE
                edhImg1?.visibility=View.INVISIBLE
                edhImg2?.visibility=View.INVISIBLE
                edhImg3?.visibility=View.INVISIBLE

                token1?.visibility=View.VISIBLE
                token2?.visibility=View.VISIBLE
                token3?.visibility=View.VISIBLE
                token1UpBtn?.visibility=View.VISIBLE
                token2UpBtn?.visibility=View.VISIBLE
                token3UpBtn?.visibility=View.VISIBLE
                token1DownBtn?.visibility=View.VISIBLE
                token2DownBtn?.visibility=View.VISIBLE
                token3DownBtn?.visibility=View.VISIBLE
                bloodImg?.visibility=View.VISIBLE
                energyImg?.visibility=View.VISIBLE
                poisonImg?.visibility=View.VISIBLE

                rightButton?.visibility=View.INVISIBLE
            }
        }

        return null
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upButton = view.findViewById(R.id.upButton)
        downButton = view.findViewById(R.id.downButton)
        leftButton = view.findViewById(R.id.leftButton)
        rightButton = view.findViewById(R.id.rightButton)
        healthCount = view.findViewById(R.id.healthTextView)
        commanderDmg1 = view.findViewById(R.id.commanderDamage1)
        commanderDmg2 = view.findViewById(R.id.commanderDamage2)
        commanderDmg3 = view.findViewById(R.id.commanderDamage3)
        cDmgUp1Btn = view.findViewById(R.id.cDmgUp1)
        cDmgUp2Btn = view.findViewById(R.id.cDmgUp2)
        cDmgUp3Btn = view.findViewById(R.id.cDmgUp3)
        cDmgDown1Btn = view.findViewById(R.id.cDmgDown1)
        cDmgDown2Btn = view.findViewById(R.id.cDmgDown2)
        cDmgDown3Btn = view.findViewById(R.id.cDmgDown3)
        token1 = view.findViewById(R.id.token1)
        token2 = view.findViewById(R.id.token2)
        token3 = view.findViewById(R.id.token3)
        token1UpBtn = view.findViewById(R.id.token1Up)
        token2UpBtn = view.findViewById(R.id.token2Up)
        token3UpBtn = view.findViewById(R.id.token3Up)
        token1DownBtn = view.findViewById(R.id.token1Down)
        token2DownBtn = view.findViewById(R.id.token2Down)
        token3DownBtn = view.findViewById(R.id.token3Down)
        bloodImg = view.findViewById(R.id.bloodImage)
        energyImg = view.findViewById(R.id.energyImage)
        poisonImg = view.findViewById(R.id.poisonImage)
        heartImg = view.findViewById(R.id.heartImage)
        edhImg1 = view.findViewById(R.id.edhImage1)
        edhImg2 = view.findViewById(R.id.edhImage2)
        edhImg3 = view.findViewById(R.id.edhImage3)

        healthCount?.setOnTouchListener { _, event ->
            when (getDirectionOfSwipe(event)) {
                "up" -> changeHealth(-5)
                "down" -> changeHealth(5)
                "bigUp" -> changeHealth(-10)
                "bigDown" -> changeHealth(10)
            }
            true
        }
        commanderDmg1?.setOnTouchListener { _, event ->
            when (getDirectionOfSwipe(event)) {
                "up" -> changeCommanderDamage(-5, 1)
                "down" -> changeCommanderDamage(5, 1)
                "bigUp" -> changeCommanderDamage(-10, 1)
                "bigDown" -> changeCommanderDamage(10, 1)
            }
            true
        }
        commanderDmg2?.setOnTouchListener { _, event ->
            when (getDirectionOfSwipe(event)) {
                "up" -> changeCommanderDamage(-5, 2)
                "down" -> changeCommanderDamage(5, 2)
                "bigUp" -> changeCommanderDamage(-10, 2)
                "bigDown" -> changeCommanderDamage(10, 2)
            }
            true
        }
        commanderDmg3?.setOnTouchListener { _, event ->
            when (getDirectionOfSwipe(event)) {
                "up" -> changeCommanderDamage(-5, 3)
                "down" -> changeCommanderDamage(5, 3)
                "bigUp" -> changeCommanderDamage(-10, 3)
                "bigDown" -> changeCommanderDamage(10, 3)
            }
            true
        }
        token1?.setOnTouchListener { _, event ->
            when (getDirectionOfSwipe(event)) {
                "up" -> changeTokens(-5, 1)
                "down" -> changeTokens(5, 1)
                "bigUp" -> changeTokens(-10, 1)
                "bigDown" -> changeTokens(10, 1)
            }
            true
        }
        token2?.setOnTouchListener { _, event ->
            when (getDirectionOfSwipe(event)) {
                "up" -> changeTokens(-5, 2)
                "down" -> changeTokens(5, 2)
                "bigUp" -> changeTokens(-10, 2)
                "bigDown" -> changeTokens(10, 2)
            }
            true
        }
        token3?.setOnTouchListener { _, event ->
            when (getDirectionOfSwipe(event)) {
                "up" -> changeTokens(-5, 3)
                "down" -> changeTokens(5, 3)
                "bigUp" -> changeTokens(-10, 3)
                "bigDown" -> changeTokens(10, 3)
            }
            true
        }
        upButton?.setOnClickListener{changeHealth(1)}
        downButton?.setOnClickListener{changeHealth(-1)}
        leftButton?.setOnClickListener{changePage(-1)}
        rightButton?.setOnClickListener{changePage(1)}
        cDmgUp1Btn?.setOnClickListener{changeCommanderDamage(1,1)}
        cDmgUp2Btn?.setOnClickListener{changeCommanderDamage(1,2)}
        cDmgUp3Btn?.setOnClickListener{changeCommanderDamage(1,3)}
        cDmgDown1Btn?.setOnClickListener{changeCommanderDamage(-1,1)}
        cDmgDown2Btn?.setOnClickListener{changeCommanderDamage(-1,2)}
        cDmgDown3Btn?.setOnClickListener{changeCommanderDamage(-1,3)}
        token1UpBtn?.setOnClickListener{changeTokens(1,1)}
        token1DownBtn?.setOnClickListener{changeTokens(-1,1)}
        token2UpBtn?.setOnClickListener{changeTokens(1,2)}
        token2DownBtn?.setOnClickListener{changeTokens(-1,2)}
        token3UpBtn?.setOnClickListener{changeTokens(1,3)}
        token3DownBtn?.setOnClickListener{changeTokens(-1,3)}

        when (this.tag) {
            "player1Fragment" -> {
                playNo = 1
                c1 = 2
                c2 = 3
                c3 = 4
                commanderDmg1?.setTextColor(resources.getColor(R.color.player2))
                commanderDmg2?.setTextColor(resources.getColor(R.color.player3))
                commanderDmg3?.setTextColor(resources.getColor(R.color.player4))
            }
            "player2Fragment" -> {
                playNo = 2
                c1 = 1
                c2 = 3
                c3 = 4
                commanderDmg1?.setTextColor(resources.getColor(R.color.player1))
                commanderDmg2?.setTextColor(resources.getColor(R.color.player3))
                commanderDmg3?.setTextColor(resources.getColor(R.color.player4))
            }
            "player3Fragment" -> {
                playNo = 3
                c1 = 1
                c2 = 2
                c3 = 4
                commanderDmg1?.setTextColor(resources.getColor(R.color.player1))
                commanderDmg2?.setTextColor(resources.getColor(R.color.player2))
                commanderDmg3?.setTextColor(resources.getColor(R.color.player4))
            }
            "player4Fragment" -> {
                playNo = 4
                c1 = 1
                c2 = 2
                c3 = 3
                commanderDmg1?.setTextColor(resources.getColor(R.color.player1))
                commanderDmg2?.setTextColor(resources.getColor(R.color.player2))
                commanderDmg3?.setTextColor(resources.getColor(R.color.player3))
            }
        }

        changePage(0)
    }

    private fun getDirectionOfSwipe(event:MotionEvent) : String {
        var x1 = 0
        var y1 = 0
        if (event.action == MotionEvent.ACTION_DOWN) {
            x1 = event.x.toInt()
            y1 = event.y.toInt()
        } else if (event.action == MotionEvent.ACTION_UP) {
            val x2 = event.x.toInt()
            val y2 = event.y.toInt()
            val dx = x2-x1
            val dy = y2-y1

            if(abs(dx) > abs(dy))
            {
                if (dx > 160)
                    changePage(1)
                else if (dx < -160)
                    changePage(-1)
            }
            else
            {
                when {
                    dy>170  -> return "bigUp"
                    dy<-170 -> return "bigDown"
                    dy>0    -> return "up"
                    dy<0    -> return "down"
                }
            }
        }
        return ""
    }
}