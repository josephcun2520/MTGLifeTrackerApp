package com.example.mtglifetrackerapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HealthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HealthFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
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

    var leftButton : ImageButton? = null
    var rightButton : ImageButton? = null
    var health : Int = 40
    var cDmg1 : Int = 0
    var cDmg2 : Int = 0
    var cDmg3 : Int = 0
    var page : Int = 1

    val pg1Views = arrayOfNulls<View>(3)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun changeHealth(amount:Int): View.OnClickListener? {
        health += amount
        healthCount?.setText(health.toString())

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
            commanderDmg3?.setText(cDmg3.toString())
        }

        return null
    }

    fun changePage(amount:Int): View.OnClickListener? {
        page += amount

        if (page > 2)
            page = 0
        else if (page < 0)
            page = 2

        if (page == 0)
        {
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
        }
        else if (page == 1)
        {
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
        }
        else if (page == 2)
        {
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
        }

        return null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_health, container, false)

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

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HealthFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HealthFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}