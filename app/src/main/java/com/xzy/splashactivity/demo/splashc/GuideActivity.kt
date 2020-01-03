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