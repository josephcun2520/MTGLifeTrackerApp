package com.example.mtglifetrackerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.VibrationEffect
import android.app.NotificationManager
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.*
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    private fun changeHealth(amount:Int): View.OnClickListener? {
        health += amount
        healthCount?.text = health.toString()

        if (amount >= 5 || amount <= -5) {
            healthCount?.animation = AnimationUtils.loadAnimation(context, R.anim.shake_animation)
            val text = if (amount < 0) {
                val posAmount = amount * -1
                "Player $playNo lost $posAmount health!"
            } else
                "Player $playNo gained $amount health!"
    //TODO: Make arrays containing page contents

    //Variables
    private lateinit var vib : Vibrator
    var deathPattern = longArrayOf(0, 250, 0, 250)
    private var players = Vector<PlayerData>()
    var gameOver = false
    var deaths = arrayOf(false, false, false, false)   //To track who is dead
    lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        notificationManager = requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        vib =  requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

        private fun winnerNotify(notifyText : String) {
        val channelID = "MTG"
        //val pendingIntent = getActivity(this, 0, intent, Context.FLAG_UPDATE_CURRENT)

        var builder = NotificationCompat.Builder(requireContext(), channelID)
        //.setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Game Over")
            .setContentText(notifyText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //.setContentIntent(pendingIntent)

        notificationManager.notify(1, builder.build())

    }

    fun changeHealth(amount:Int): View.OnClickListener? {
        var text = ""
        if (health <= 0) {  //Dead
            health = 0
            healthCount?.setText((health.toString()))
            vib.vibrate(VibrationEffect.createWaveform(deathPattern, -1))

                //TODO: notify of death here or in commander damage
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

        return null
    }

    private fun changeCommanderDamage(amount:Int, player:Int):View.OnClickListener? {
        when (player) {
            1 -> {
                cDmg1 += amount
                commanderDmg1?.text = cDmg1.toString()
                if (cDmg1 >= 21)
                {
                    //vib.vibrate(VibrationEffect.createWaveform(deathPattern, -1))
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
                    //vib.vibrate(VibrationEffect.createWaveform(deathPattern, -1))
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
                    //vib.vibrate(VibrationEffect.createWaveform(deathPattern, -1))
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
                "up" -> changeTokens(-5, 2)
                "down" -> changeTokens(5, 2)
                "bigUp" -> changeTokens(-10, 2)
                "bigDown" -> changeTokens(10, 2)
            }
            true
        }
        token2?.setOnTouchListener { _, event ->
            when (getDirectionOfSwipe(event)) {
                "up" -> changeTokens(-5, 3)
                "down" -> changeTokens(5, 3)
                "bigUp" -> changeTokens(-10, 3)
                "bigDown" -> changeTokens(10, 3)
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
                if (dx>0)
                    changePage(1)
                else if (dx<0)
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