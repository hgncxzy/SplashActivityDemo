package com.xzy.splashactivity.demo.splasha

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import androidx.appcompat.app.AppCompatActivity
import com.xzy.splashactivity.demo.MainActivity
import com.xzy.splashactivity.demo.R
import com.xzy.splashactivity.demo.functions.Function.hideStatusBar

class SplashActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 隐藏状态栏
        hideStatusBar(window)
        val view = View.inflate(this, R.layout.activity_splash1, null)
        setContentView(view)
        // 设置渐变动画
        val animation = AlphaAnimation(0.6f, 1.0f)
        animation.duration = 1500
        view.startAnimation(animation)
        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })

        // or
//        Handler().postDelayed({
//            redirectTo()
//        }, 1500)
    }

    /**
     * 跳转到...
     */
    private fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
