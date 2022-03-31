package com.example.mtglifetrackerapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import com.example.mtglifetrackerapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.infoButton.setOnClickListener {openIntroScreen()}
        binding.diceButton.setOnClickListener {openDiceScreen()}

        binding.fragmentContainerView.animation = AnimationUtils.loadAnimation(this,R.anim.slide_up)
        binding.fragmentContainerView2.animation = AnimationUtils.loadAnimation(this,R.anim.slide_down)
        binding.fragmentContainerView3.animation = AnimationUtils.loadAnimation(this,R.anim.slide_up)
        binding.fragmentContainerView4.animation = AnimationUtils.loadAnimation(this,R.anim.slide_down)
    }

    private fun openDiceScreen(){
        val newFragment = DiceFragment()
        newFragment.show(supportFragmentManager, "game")
    }
    private fun openIntroScreen(){
        val newFragment = InfoFragment()
        newFragment.show(supportFragmentManager,"game")
    }
}