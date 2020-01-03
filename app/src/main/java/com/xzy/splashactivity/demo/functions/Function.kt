package com.xzy.splashactivity.demo.functions

import android.view.Window
import android.view.WindowManager

/**
 * Author: xzy
 */
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

}