package com.haocxx.haocxxframework.util.view

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.View
import android.view.WindowManager

/**
 * Created by Haocxx
 * on 2018/10/9
 */
object ViewUtil {
    var mFullScrennOriginalHeight = 0
    var mFullScrennOriginalWidth = 0

    fun enterFullScrenn(activity : Activity, viewBox : View) {
        // hide status bar
        val params = activity.window.attributes
        params.flags = params.flags.or(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity.window.attributes = params
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // get height and width of screen
        val displayMetrics = activity.resources.displayMetrics
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        // scale view
        val layoutParams = viewBox.layoutParams
        mFullScrennOriginalHeight = layoutParams.height
        mFullScrennOriginalWidth = layoutParams.width
        layoutParams.height = width
        layoutParams.width = height
        viewBox.layoutParams = layoutParams

        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    fun exitFullScrenn(activity : Activity, viewBox : View) {
        val params = activity.window.attributes
        params.flags = params.flags.and(WindowManager.LayoutParams.FLAG_FULLSCREEN.inv())
        activity.window.attributes = params
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val layoutParams = viewBox.layoutParams
        layoutParams.height = mFullScrennOriginalHeight
        layoutParams.width = mFullScrennOriginalWidth
        viewBox.layoutParams = layoutParams

        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}