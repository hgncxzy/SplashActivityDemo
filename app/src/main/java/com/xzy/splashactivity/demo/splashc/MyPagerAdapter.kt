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