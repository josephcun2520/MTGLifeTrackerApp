package com.example.mtglifetrackerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class InfoFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Apply the xml file
        return inflater.inflate(R.layout.fragment_info, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dismissButton : Button = view.findViewById(R.id.dismissButton)
        dismissButton.setOnClickListener { dismiss() }
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {       //Make box fit screen size
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog!!.window!!.setLayout(width, height)
        }
    }
}