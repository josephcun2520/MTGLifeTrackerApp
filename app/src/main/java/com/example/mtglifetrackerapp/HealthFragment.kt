package com.example .mtglifetrackerapp

import android.os.Bundle
import android.os.Handler
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class HealthFragment : Fragment() {
    var x1 = 0
    var x2 = 0
    var y1 = 0
    var y2 = 0
    var dx = 0
    var dy = 0
    var healthCount : TextView? = null
    var commanderDmg1 : TextView? = null
    var commanderDmg2 : TextView? = null
    var commanderDmg3: TextView? = null
    var cDmgUp1Btn : ImageButton? = null
    var cDmgUp2Btn : ImageButton? = null
    var cDmgUp3Btn : ImageButton? = null
    var cDmgDown1Btn : ImageButton? = null
    var cDmgDown2Btn : ImageButton? = null
    var cDmgDown3Btn : ImageButton? = null
    var upButton : ImageButton? = null
    var downButton : ImageButton? = null
    var bloodImg : ImageView? = null
    var energyImg : ImageView? = null
    var poisonImg : ImageView? = null
    var token1 : TextView? = null
    var token2 : TextView? = null
    var token3 : TextView? = null
    var token1UpBtn : ImageButton? = null
    var token2UpBtn : ImageButton? = null
    var token3UpBtn : ImageButton? = null
    var token1DownBtn : ImageButton? = null
    var token2DownBtn : ImageButton? = null
    var token3DownBtn : ImageButton? = null
    var bloodTokens = 0
    var energyTokens = 0
    var poisonTokens = 0
    var leftButton : ImageButton? = null
    var rightButton : ImageButton? = null
    var health : Int = 40
    var cDmg1 : Int = 0
    var cDmg2 : Int = 0
    var cDmg3 : Int = 0
    var page : Int = 1
    var playNo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun changeHealth(amount:Int): View.OnClickListener? {
        health += amount
        healthCount?.setText(health.toString())

        if (amount >= 5 || amount <= -5) {
            healthCount?.animation = AnimationUtils.loadAnimation(context,R.anim.shake_animation)
            var text = ""
            if (amount < 0) {
                val posAmount = amount * -1
                text = "Player $playNo lost $posAmount health!"
            }
            else
            {
                text = "Player $playNo gained $amount health!"
            }
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

        return null
    }

    fun changeCommanderDamage(amount:Int,player:Int):View.OnClickListener? {
        if (player == 1){
            cDmg1 += amount
            commanderDmg1?.setText(cDmg1.toString())
        }
        else if (player == 2) {
            cDmg2 += amount
            commanderDmg2?.setText(cDmg2.toString())
        }
        else if (player == 3) {
            cDmg3 += amount
            commanderDmg3?.text = cDmg3.toString()
        }

        return null
    }

    fun changeTokens(amount:Int,type:Int):View.OnClickListener? {
        if (type == 1){
            bloodTokens += amount
            token1?.setText(bloodTokens.toString())
        }
        else if (type == 2) {
            energyTokens += amount
            token2?.setText(energyTokens.toString())
        }
        else if (type == 3) {
            poisonTokens += amount
            token3?.text = poisonTokens.toString()

            if (poisonTokens >= 10 && health > 0)
            {
                health = 0
                healthCount?.text = health.toString()
                var text = "Player $playNo dies to poison!"
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
            page = 0
        else if (page < 0)
            page = 2

        when (page) {
            0 -> {
                healthCount?.visibility=View.INVISIBLE
                upButton?.visibility=View.INVISIBLE
                downButton?.visibility=View.INVISIBLE

                commanderDmg1?.visibility=View.VISIBLE
                commanderDmg2?.visibility=View.VISIBLE
                commanderDmg3?.visibility=View.VISIBLE
                cDmgUp1Btn?.visibility=View.VISIBLE
                cDmgUp2Btn?.visibility=View.VISIBLE
                cDmgUp3Btn?.visibility=View.VISIBLE
                cDmgDown1Btn?.visibility=View.VISIBLE
                cDmgDown2Btn?.visibility=View.VISIBLE
                cDmgDown3Btn?.visibility=View.VISIBLE

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
            }
            1 -> {
                healthCount?.visibility=View.VISIBLE
                upButton?.visibility=View.VISIBLE
                downButton?.visibility=View.VISIBLE

                commanderDmg1?.visibility=View.INVISIBLE
                commanderDmg2?.visibility=View.INVISIBLE
                commanderDmg3?.visibility=View.INVISIBLE
                cDmgUp1Btn?.visibility=View.INVISIBLE
                cDmgUp2Btn?.visibility=View.INVISIBLE
                cDmgUp3Btn?.visibility=View.INVISIBLE
                cDmgDown1Btn?.visibility=View.INVISIBLE
                cDmgDown2Btn?.visibility=View.INVISIBLE
                cDmgDown3Btn?.visibility=View.INVISIBLE

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
            }
            2 -> {
                healthCount?.visibility=View.INVISIBLE
                upButton?.visibility=View.INVISIBLE
                downButton?.visibility=View.INVISIBLE

                commanderDmg1?.visibility=View.INVISIBLE
                commanderDmg2?.visibility=View.INVISIBLE
                commanderDmg3?.visibility=View.INVISIBLE
                cDmgUp1Btn?.visibility=View.INVISIBLE
                cDmgUp2Btn?.visibility=View.INVISIBLE
                cDmgUp3Btn?.visibility=View.INVISIBLE
                cDmgDown1Btn?.visibility=View.INVISIBLE
                cDmgDown2Btn?.visibility=View.INVISIBLE
                cDmgDown3Btn?.visibility=View.INVISIBLE

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
            }
        }

        return null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

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

        upButton?.setOnClickListener{changeHealth(1)}

        //TODO: Move on touch listener to separate function so that it can be used for many elements without needing 30 lines for each
        healthCount?.setOnTouchListener(OnTouchListener { v, event ->
            val direction : String = getDirectionOfSwipe(event)
            if (direction == "up")
            {
                changeHealth(-5)
            }
            else if (direction == "down")
            {
                changeHealth(5)
            }
            else if (direction == "bigUp")
            {
                changeHealth(-10)
            }
            else if (direction == "bigDown")
            {
                changeHealth(10)
            }
            true
        })

        commanderDmg1?.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                x1 = event.getX().toInt()
                y1 = event.getY().toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                x2 = event.getX().toInt()
                y2 = event.getY().toInt()
                dx = x2-x1
                dy = y2-y1

                //TODO: Make this function take distance/angle into account

                if(Math.abs(dx) > Math.abs(dy))
                {
                    if (dx>0){
                        changePage(-1)
                    }
                    else if (dx<0)
                    {
                        changePage(1)
                    }
                }
                else
                {
                    if (dy>0)   //up
                        changeCommanderDamage(-1,1)
                    else if (dy<0)  //down
                        changeCommanderDamage(1,1)
                }
            }
            true
        })

        commanderDmg2?.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                x1 = event.getX().toInt()
                y1 = event.getY().toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                x2 = event.getX().toInt()
                y2 = event.getY().toInt()
                dx = x2-x1
                dy = y2-y1

                //TODO: Make this function take distance/angle into account

                if(Math.abs(dx) > Math.abs(dy))
                {
                    if (dx>0){
                        changePage(-1)
                    }
                    else if (dx<0)
                    {
                        changePage(1)
                    }
                }
                else
                {
                    if (dy>0)   //up
                        changeCommanderDamage(-1,2)
                    else if (dy<0)  //down
                        changeCommanderDamage(1,2)
                }
            }
            true
        })

        commanderDmg3?.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                x1 = event.getX().toInt()
                y1 = event.getY().toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                x2 = event.getX().toInt()
                y2 = event.getY().toInt()
                dx = x2-x1
                dy = y2-y1

                //TODO: Make this function take distance/angle into account

                if(Math.abs(dx) > Math.abs(dy))
                {
                    if (dx>0){
                        changePage(-1)
                    }
                    else if (dx<0)
                    {
                        changePage(1)
                    }
                }
                else
                {
                    if (dy>0)   //up
                        changeCommanderDamage(-1,3)
                    else if (dy<0)  //down
                        changeCommanderDamage(1,3)
                }
            }
            true
        })

        token1?.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                x1 = event.getX().toInt()
                y1 = event.getY().toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                x2 = event.getX().toInt()
                y2 = event.getY().toInt()
                dx = x2-x1
                dy = y2-y1

                //TODO: Make this function take distance/angle into account

                if(Math.abs(dx) > Math.abs(dy))
                {
                    if (dx>0){
                        changePage(-1)
                    }
                    else if (dx<0)
                    {
                        changePage(1)
                    }
                }
                else
                {
                    if (dy>0)   //up
                        changeTokens(-1,1)
                    else if (dy<0)  //down
                        changeTokens(1,1)
                }
            }
            true
        })

        token2?.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                x1 = event.getX().toInt()
                y1 = event.getY().toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                x2 = event.getX().toInt()
                y2 = event.getY().toInt()
                dx = x2-x1
                dy = y2-y1

                //TODO: Make this function take distance/angle into account

                if(Math.abs(dx) > Math.abs(dy))
                {
                    if (dx>0){
                        changePage(-1)
                    }
                    else if (dx<0)
                    {
                        changePage(1)
                    }
                }
                else
                {
                    if (dy>0)   //up
                        changeTokens(-1,2)
                    else if (dy<0)  //down
                        changeTokens(1,2)
                }
            }
            true
        })

        token3?.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                x1 = event.getX().toInt()
                y1 = event.getY().toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                x2 = event.getX().toInt()
                y2 = event.getY().toInt()
                dx = x2-x1
                dy = y2-y1

                //TODO: Make this function take distance/angle into account

                if(Math.abs(dx) > Math.abs(dy))
                {
                    if (dx>0){
                        changePage(-1)
                    }
                    else if (dx<0)
                    {
                        changePage(1)
                    }
                }
                else
                {
                    if (dy>0)   //up
                        changeTokens(-1,3)
                    else if (dy<0)  //down
                        changeTokens(1,3)
                }
            }
            true
        })

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
            }
            "player2Fragment" -> {
                playNo = 2
            }
            "player3Fragment" -> {
                playNo = 3
            }
            "player4Fragment" -> {
                playNo = 4
            }
        }

        val handler = Handler()
        handler.postDelayed(Runnable {
            // yourMethod();
            var text = "Test"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

        }, 1000) //5 seconds

    }

    fun getDirectionOfSwipe(event:MotionEvent) : String {
        if (event.action == MotionEvent.ACTION_DOWN) {
            x1 = event.getX().toInt()
            y1 = event.getY().toInt()
        } else if (event.action == MotionEvent.ACTION_UP) {
            x2 = event.getX().toInt()
            y2 = event.getY().toInt()
            dx = x2-x1
            dy = y2-y1

            if(Math.abs(dx) > Math.abs(dy))
            {
                if (dx>0){
                    changePage(-1)
                }
                else if (dx<0)
                {
                    changePage(1)
                }
            }
            else
            {
                if (dy>150)   //up
                    return "bigUp"
                else if (dy<-150)  //down
                    return "bigDown"
                else if (dy>0)   //up
                    return "up"
                else if (dy<0)  //down
                    return "down"
            }
        }
        return ""
    }
}