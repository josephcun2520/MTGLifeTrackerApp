package com.example.mtglifetrackerapp

import android.app.Application
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
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

class MyApplication : Application() {
    var shouldVibrate : Boolean = true
    var shouldToast : Boolean = true
    var mute : Boolean = false
    var shouldAnimate : Boolean = true
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
