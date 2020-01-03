package com.xzy.splashactivity.demo.splashc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import androidx.appcompat.app.AppCompatActivity
import com.xzy.splashactivity.demo.MainActivity
import com.xzy.splashactivity.demo.R
import com.xzy.splashactivity.demo.functions.Function.hideStatusBar

/**
 * reference https://blog.csdn.net/jxnk25/article/details/47679021
 * App launch page with viewpager,include 4 images.
 * */
class SplashActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 隐藏状态栏
        hideStatusBar(window)
        setContentView(R.layout.activity_splash3)
        Handler().postDelayed({
            redirectTo()
        }, 1500)
    }

    /**
     * 跳转到...
     */
    private fun redirectTo() {
        val intent = Intent(this, GuideActivity::class.java)
        startActivity(intent)
        finish()
    }
}
