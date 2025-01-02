package com.example.biasaaja_companyprofile.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.biasaaja_companyprofile.R
import com.example.biasaaja_companyprofile.databinding.ActivityHelper2Binding

class Helper2Activity : AppCompatActivity() {
    companion object{
        var UsernameHelper = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_helper2)
        UsernameHelper = intent.getStringExtra("username").toString()
    }

}