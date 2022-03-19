package com.example.mtglifetrackerapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    var upButton : Button? = null
    var downButton : Button? = null
    var health : Int = 40

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
        if (health > 29)
            view?.setBackgroundColor(Color.GREEN)
        if (health in 16..29)
            view?.setBackgroundColor(Color.YELLOW)
        else if (health < 15)
            view?.setBackgroundColor(Color.RED)
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
        healthCount = view.findViewById(R.id.healthTextView)
        upButton?.setOnClickListener{changeHealth(1)}
        downButton?.setOnClickListener{changeHealth(-1)}
        view.setBackgroundColor(Color.GREEN)
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