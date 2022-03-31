package com.example.mtglifetrackerapp

import android.os.Bundle
import android.os.Handler
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
import com.example.mtglifetrackerapp.databinding.FragmentHealthBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var binding : FragmentHealthBinding

class HealthFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var x1 = 0
    var x2 = 0
    var y1 = 0
    var y2 = 0
    var dx = 0
    var dy = 0

    var bloodTokens = 0
    var energyTokens = 0
    var poisonTokens = 0

    var health : Int = 40
    var cDmg1 : Int = 0
    var cDmg2 : Int = 0
    var cDmg3 : Int = 0
    var page : Int = 1
    var playNo = 0

    val pg1Views = arrayOfNulls<View>(3)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHealthBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    fun changeHealth(amount:Int): View.OnClickListener? {
        health += amount
        binding.healthTextView.setText(health.toString())

        if (amount >= 5 || amount <= -5) {
            binding.healthTextView.animation = AnimationUtils.loadAnimation(context,R.anim.shake_animation)

        }

        var text = if (amount < 0) {
            val posAmount = amount * -1
            "Player $playNo lost $posAmount health!"
        } else {
            "Player $playNo gained $amount health!"
        }
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(context, text, duration)
        toast.show()

        return null
    }

    fun changeCommanderDamage(amount:Int,player:Int):View.OnClickListener? {
        if (player == 1){
            cDmg1 += amount
            binding.commanderDamage1?.setText(cDmg1.toString())
        }
        else if (player == 2) {
            cDmg2 += amount
            binding.commanderDamage2?.setText(cDmg2.toString())
        }
        else if (player == 3) {
            cDmg3 += amount
            binding.commanderDamage3?.text = cDmg3.toString()
        }

        return null
    }

    fun changeTokens(amount:Int,type:Int):View.OnClickListener? {
        if (type == 1){
            bloodTokens += amount
            binding.token1.setText(bloodTokens.toString())
        }
        else if (type == 2) {
            energyTokens += amount
            binding.token2.setText(energyTokens.toString())
        }
        else if (type == 3) {
            poisonTokens += amount
            binding.token3.text = poisonTokens.toString()

            if (poisonTokens >= 10 && health > 0)
            {
                health = 0
                binding.healthTextView.text = health.toString()
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
                binding.healthTextView.visibility=View.INVISIBLE
                binding.upButton.visibility=View.INVISIBLE
                binding.downButton.visibility=View.INVISIBLE

                binding.commanderDamage1.visibility=View.VISIBLE
                binding.commanderDamage2.visibility=View.VISIBLE
                binding.commanderDamage3.visibility=View.VISIBLE
                binding.cDmgUp1.visibility=View.VISIBLE
                binding.cDmgUp2.visibility=View.VISIBLE
                binding.cDmgUp3.visibility=View.VISIBLE
                binding.cDmgDown1.visibility=View.VISIBLE
                binding.cDmgDown2.visibility=View.VISIBLE
                binding.cDmgDown3.visibility=View.VISIBLE

                binding.token1.visibility=View.INVISIBLE
                binding.token2.visibility=View.INVISIBLE
                binding.token3.visibility=View.INVISIBLE
                binding.token1Up.visibility=View.INVISIBLE
                binding.token2Up.visibility=View.INVISIBLE
                binding.token3Up.visibility=View.INVISIBLE
                binding.token1Down.visibility=View.INVISIBLE
                binding.token2Down.visibility=View.INVISIBLE
                binding.token3Down.visibility=View.INVISIBLE
                binding.bloodImage.visibility=View.INVISIBLE
                binding.energyImage.visibility=View.INVISIBLE
                binding.poisonImage.visibility=View.INVISIBLE
            }
            1 -> {
                binding.healthTextView.visibility=View.VISIBLE
                binding.upButton.visibility=View.VISIBLE
                binding.downButton.visibility=View.VISIBLE

                binding.commanderDamage1.visibility=View.INVISIBLE
                binding.commanderDamage2.visibility=View.INVISIBLE
                binding.commanderDamage3.visibility=View.INVISIBLE
                binding.cDmgUp1.visibility=View.INVISIBLE
                binding.cDmgUp2.visibility=View.INVISIBLE
                binding.cDmgUp3.visibility=View.INVISIBLE
                binding.cDmgDown1.visibility=View.INVISIBLE
                binding.cDmgDown2.visibility=View.INVISIBLE
                binding.cDmgDown3.visibility=View.INVISIBLE

                binding.token1.visibility =View.INVISIBLE
                binding.token2.visibility=View.INVISIBLE
                binding.token3.visibility=View.INVISIBLE
                binding.token1Up.visibility=View.INVISIBLE
                binding.token2Up.visibility=View.INVISIBLE
                binding.token3Up.visibility=View.INVISIBLE
                binding.token1Down.visibility=View.INVISIBLE
                binding.token2Down.visibility=View.INVISIBLE
                binding.token3Down.visibility=View.INVISIBLE
                binding.bloodImage.visibility=View.INVISIBLE
                binding.energyImage.visibility=View.INVISIBLE
                binding.poisonImage.visibility=View.INVISIBLE
            }
            2 -> {
                binding.healthTextView.visibility=View.INVISIBLE
                binding.upButton.visibility=View.INVISIBLE
                binding.downButton.visibility=View.INVISIBLE

                binding.commanderDamage1.visibility=View.INVISIBLE
                binding.commanderDamage2.visibility=View.INVISIBLE
                binding.commanderDamage3.visibility=View.INVISIBLE
                binding.cDmgUp1.visibility=View.INVISIBLE
                binding.cDmgUp2.visibility=View.INVISIBLE
                binding.cDmgUp3.visibility=View.INVISIBLE
                binding.cDmgDown1.visibility=View.INVISIBLE
                binding.cDmgDown2.visibility=View.INVISIBLE
                binding.cDmgDown3.visibility=View.INVISIBLE

                binding.token1.visibility=View.VISIBLE
                binding.token2.visibility=View.VISIBLE
                binding.token3.visibility=View.VISIBLE
                binding.token1Up.visibility=View.VISIBLE
                binding.token2Up.visibility=View.VISIBLE
                binding.token3Up.visibility=View.VISIBLE
                binding.token1Down.visibility=View.VISIBLE
                binding.token2Down.visibility=View.VISIBLE
                binding.token3Down.visibility=View.VISIBLE
                binding.bloodImage.visibility=View.VISIBLE
                binding.energyImage.visibility=View.VISIBLE
                binding.poisonImage.visibility=View.VISIBLE
            }
        }

        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Move on touch listener to separate function so that it can be used for many elements without needing 30 lines for each
        binding.healthTextView.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                x1 = event.getX().toInt()
                y1 = event.getY().toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                x2 = event.getX().toInt()
                y2 = event.getY().toInt()
                dx = x2-x1
                dy = y2-y1

                var text = "dx=$dx, dy=$dy"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()

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
                    if (dy>170)   //up
                    {
                        changeHealth(-10)
                    }
                    else if (dy<-170)  //down
                        changeHealth(10)
                    else if (dy>0)   //up
                        changeHealth(-5)
                    else if (dy<0)  //down
                        changeHealth(5)
                }
            }
            true
        })

        binding.commanderDamage1.setOnTouchListener(OnTouchListener { v, event ->
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

        binding.commanderDamage2.setOnTouchListener(OnTouchListener { v, event ->
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

        binding.commanderDamage3.setOnTouchListener(OnTouchListener { v, event ->
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

        binding.token1?.setOnTouchListener(OnTouchListener { v, event ->
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

        binding.token2?.setOnTouchListener(OnTouchListener { v, event ->
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

        binding.token3?.setOnTouchListener(OnTouchListener { v, event ->
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

        binding.upButton.setOnClickListener{changeHealth(1)}
        binding.downButton.setOnClickListener{changeHealth(-1)}
        binding.leftButton.setOnClickListener{changePage(-1)}
        binding.rightButton.setOnClickListener{changePage(1)}
        binding.cDmgUp1.setOnClickListener{changeCommanderDamage(1,1)}
        binding.cDmgUp2.setOnClickListener{changeCommanderDamage(1,2)}
        binding.cDmgUp3.setOnClickListener{changeCommanderDamage(1,3)}
        binding.cDmgDown1.setOnClickListener{changeCommanderDamage(-1,1)}
        binding.cDmgDown2.setOnClickListener{changeCommanderDamage(-1,2)}
        binding.cDmgDown3.setOnClickListener{changeCommanderDamage(-1,3)}
        binding.token1Up.setOnClickListener{changeTokens(1,1)}
        binding.token1Down.setOnClickListener{changeTokens(-1,1)}
        binding.token2Up.setOnClickListener{changeTokens(1,2)}
        binding.token2Down.setOnClickListener{changeTokens(-1,2)}
        binding.token3Up.setOnClickListener{changeTokens(1,3)}
        binding.token3Down.setOnClickListener{changeTokens(-1,3)}

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
            val text = "Test"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

        }, 1000)

    }
}
