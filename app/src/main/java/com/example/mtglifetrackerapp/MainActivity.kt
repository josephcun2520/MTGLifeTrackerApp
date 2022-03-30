package com.example.mtglifetrackerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.fragment.app.FragmentContainer


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val diceButton : ImageButton = findViewById(R.id.diceButton)
        val p1fragment : View? = findViewById(R.id.fragmentContainerView)
        val p2fragment : View? = findViewById(R.id.fragmentContainerView2)
        val p3fragment : View? = findViewById(R.id.fragmentContainerView3)
        val p4fragment : View? = findViewById(R.id.fragmentContainerView4)

        diceButton?.setOnClickListener {openDiceScreen()}
        p1fragment?.animation = AnimationUtils.loadAnimation(this,R.anim.slide_up)
        p2fragment?.animation = AnimationUtils.loadAnimation(this,R.anim.slide_down)
        p3fragment?.animation = AnimationUtils.loadAnimation(this,R.anim.slide_up)
        p4fragment?.animation = AnimationUtils.loadAnimation(this,R.anim.slide_down)
    }

    private fun openDiceScreen(){
        val newFragment = DiceFragment()
        newFragment.show(supportFragmentManager, "game")
    }
}