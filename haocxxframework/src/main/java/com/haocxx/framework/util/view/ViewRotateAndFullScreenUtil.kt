package com.haocxx.framework.util.view

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.View
import android.view.WindowManager

/**
 * Rotate a view to landscape and scale it match the full screen. Function for
 * some views like video player.
 *
 * Created by Haocxx
 * on 2018/10/9
 */
object ViewRotateAndFullScreenUtil {
    var mFullScreenOriginalHeight = 0
    var mFullScreenOriginalWidth = 0

    /**
     * Turn orientation and scale view to full screen.
     * @param activity The context activity of the view to be scaled.
     * @param viewBox The view to be scaled.
     */
    fun enterFullScreen(activity : Activity, viewBox : View) {
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
        mFullScreenOriginalHeight = layoutParams.height
        mFullScreenOriginalWidth = layoutParams.width
        layoutParams.height = width
        layoutParams.width = height
        viewBox.layoutParams = layoutParams

        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    /**
     * Revert the full screen state of a view invoke method {@link #enterFullScreen}
     * @param activity The context activity of the view to be reverted.
     * @param viewBox The view to be reverted.
     */
    fun exitFullScreen(activity : Activity, viewBox : View) {
        val params = activity.window.attributes
        params.flags = params.flags.and(WindowManager.LayoutParams.FLAG_FULLSCREEN.inv())
        activity.window.attributes = params
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val layoutParams = viewBox.layoutParams
        layoutParams.height = mFullScreenOriginalHeight
        layoutParams.width = mFullScreenOriginalWidth
        viewBox.layoutParams = layoutParams

        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}