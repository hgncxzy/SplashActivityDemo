# SplashActivityDemo
App 启动页的实现小栗子.

### 效果

![https://github.com/hgncxzy/SplashActivityDemo/blob/master/img/1.gif](https://github.com/hgncxzy/SplashActivityDemo/blob/master/img/1.gif)

### App 启动页加载一张图片

典型的启动页面，加载一张图片，若干秒后跳转到主页面。

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

在典型的启动页基础上，在右上角添加倒计时功能，可以点击跳过，倒计时结束，进入首页。

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

### 使用 ViewPager 实现滑动启动页

包含若干张图片，可以左右滑动，在最后一张图片不居中，点击按钮跳转到主页。底部有小点点，代表滑动到了哪个页面。还有个细节需要注意的是，只有第一次进入 App 才会加载引导页，非第一次进入，直接进入首页即可。

```kotlin
 /**
     * 跳转到...
     */
    private fun redirectTo() {
        val intent = Intent()
        if (isFirstRun(this)) {
            // 第一次进入，显示引导页
            intent.setClass(this, GuideActivity::class.java)
        } else {
            // 非第一次进入，不显示引导页
            intent.setClass(this, MainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
```

参考 https://blog.csdn.net/jxnk25/article/details/47679021

```kotlin
package com.xzy.splashactivity.demo.splashc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.xzy.splashactivity.demo.MainActivity
import com.xzy.splashactivity.demo.R
import kotlinx.android.synthetic.main.activity_guide.*
import java.util.*

@Suppress("DEPRECATION")
class GuideActivity : AppCompatActivity() {
    private var startBtn:Button? = null
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 设置全屏
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_guide)

        viewpager.setOnPageChangeListener(MyOnPageChangeListener())
        // 这里是每一页要显示的布局，根据应用需要和特点自由设计显示的内容 以及需要显示多少页等
        val mLi = LayoutInflater.from(this)
        val view1: View = mLi.inflate(R.layout.whats_news_gallery_one, null)
        val view2: View = mLi.inflate(R.layout.whats_news_gallery_two, null)
        val view3: View = mLi.inflate(R.layout.whats_news_gallery_three, null)
        val view4: View = mLi.inflate(R.layout.whats_news_gallery_four, null)
        // 在第四个界面上绑定button
        startBtn = view4.findViewById(R.id.start_btn)
        startBtn?.setOnClickListener(BtnClickListener())
        // 这里将每一页显示的view存放到ArrayList集合中 可以在ViewPager适配器中顺序调用展示
        val views = ArrayList<View>()
        views.add(view1)
        views.add(view2)
        views.add(view3)
        views.add(view4)
        // 每个页面的Title数据存放到ArrayList集合中 可以在ViewPager适配器中调用展示
        val titles = ArrayList<String>()
        titles.add("tab1")
        titles.add("tab2")
        titles.add("tab3")
        titles.add("tab4")
        // 填充ViewPager的数据适配器
        val mPagerAdapter = MyPagerAdapter(views, titles)
        viewpager.adapter = mPagerAdapter
    }

    inner class MyOnPageChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageSelected(page: Int) { // 翻页时当前page,改变当前状态园点图片
            when (page) {
                0 -> {
                    page0.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page_now
                        )
                    )
                    page1.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page
                        )
                    )
                }
                1 -> {
                    page1.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page_now
                        )
                    )
                    page0.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page
                        )
                    )
                    page2.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page
                        )
                    )
                }
                2 -> {
                    page2.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page_now
                        )
                    )
                    page1.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page
                        )
                    )
                    page3.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page
                        )
                    )
                }
                3 -> {
                    page3.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page_now
                        )
                    )
                    page2.setImageDrawable(
                        resources.getDrawable(
                            R.drawable.page
                        )
                    )
                }
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    inner class BtnClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            val it = Intent(this@GuideActivity, MainActivity::class.java)
            startActivity(it) // 点击最后一页的button进入主界面
            overridePendingTransition(
                R.anim.new_dync_in_from_right, R.anim.new_dync_out_to_left
            )
            finish()
        }
    }
}
```

适配器，很简单

```kotlin
package com.xzy.splashactivity.demo.splashc

import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.*

/**
 * ViewPager适配器，用来绑定数据和 view
 *
 * @author xzy
 */
@Suppress("unused")
class MyPagerAdapter(
    /**
     * 界面列表
     */
    private val views: ArrayList<View>,
    private val titles: ArrayList<String>
) : PagerAdapter() {
    /**
     * 获取当前页面数
     */
    override fun getCount(): Int {
        return views.size
    }

    /**
     * 判断是否由对象生成界面
     */
    override fun isViewFromObject(
        arg0: View,
        arg1: Any
    ): Boolean {
        return arg0 === arg1
    }

    /**
     * 销毁position位置的界面
     */
    override fun destroyItem(
        container: View,
        position: Int,
        `object`: Any
    ) {
        (container as ViewPager).removeView(views[position])
    }

    /**
     * 初始化position位置的界面
     */
    override fun instantiateItem(container: View, position: Int): Any {
        (container as ViewPager).addView(views[position])
        return views[position]
    }

}
```

