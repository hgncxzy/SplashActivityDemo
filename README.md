# SplashActivityDemo
App 启动页的实现小栗子.

### App 启动页加载一张图片

实现步骤请参考 https://blog.csdn.net/jdfkldjlkjdl/article/details/103816817

```kotlin
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

```



### App 启动页加载一张图片 +  倒计时 

```kotlin
package com.xzy.splashactivity.demo.splashb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xzy.splashactivity.demo.MainActivity
import com.xzy.splashactivity.demo.R
import com.xzy.splashactivity.demo.functions.Function.hideStatusBar
import kotlinx.android.synthetic.main.activity_splash2.*
import java.util.*

class SplashActivity2 : AppCompatActivity() {
    private var countDownTimeLong = 5 // 倒计时 5 秒钟
    private val timer = Timer()
    private var task: TimerTask = object : TimerTask() {
        @SuppressLint("SetTextI18n")
        override fun run() {
            runOnUiThread {
                countDownTimeLong--
                tv_time.text = "跳过 $countDownTimeLong 秒"
                if (countDownTimeLong < 0) {
                    timer.cancel()
                    //倒计时到 0 隐藏字体
                    tv_time.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 隐藏状态栏
        hideStatusBar(window)
        setContentView(R.layout.activity_splash2)
        //等待时间一秒，停顿时间一秒
        timer.schedule(task, 1000, 1000)
        // 正常情况 -- 倒计时结束跳转
        Handler().postDelayed({
            redirectTo()
        }, 5000)

        // 点击取消倒计时，直接跳转
        tv_time.setOnClickListener {
            timer.cancel()
            redirectTo()
        }
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

```

