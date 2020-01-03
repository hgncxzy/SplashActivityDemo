package com.xzy.splashactivity.demo.splasha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.xzy.splashactivity.demo.MainActivity
import com.xzy.splashactivity.demo.R
import com.xzy.splashactivity.demo.functions.Function.hideStatusBar

class SplashActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar(window)
        setContentView(R.layout.activity_splash1)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },1500)
    }
}
