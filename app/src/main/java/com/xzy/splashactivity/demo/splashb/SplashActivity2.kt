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
    var task: TimerTask = object : TimerTask() {
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
