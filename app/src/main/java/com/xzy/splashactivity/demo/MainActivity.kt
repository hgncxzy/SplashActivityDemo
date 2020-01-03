package com.xzy.splashactivity.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xzy.splashactivity.demo.splasha.SplashActivity1
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_splash_load_img.setOnClickListener {
            startActivity(Intent(this, SplashActivity1::class.java))
        }
    }
}