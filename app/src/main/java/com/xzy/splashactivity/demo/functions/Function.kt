package com.xzy.splashactivity.demo.functions

import android.content.Context
import android.content.SharedPreferences
import android.view.Window
import android.view.WindowManager

/**
 * Author: xzy
 */
@Suppress("unused")
object Function {

    fun hideStatusBar(window: Window) {
        val attrs: WindowManager.LayoutParams = window.attributes
        attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
        window.attributes = attrs
    }

    fun showStatusBar(window: Window) {
        val attrs: WindowManager.LayoutParams = window.attributes
        attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
        window.attributes = attrs
    }

    /**
     * 判断是否是第一次启动程序 利用 SharedPreferences 将数据保存在本地
     * */
    fun isFirstRun(context: Context): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(
            "share", Context.MODE_PRIVATE
        )
        val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)
        val editor = sharedPreferences.edit()
        return if (!isFirstRun) {
            false
        } else {
            editor.putBoolean("isFirstRun", false)
            editor.apply()
            true
        }
    }

}