package com.haocxx.framework.util.system

import android.util.Log

/**
 * A wrapper LogUtil.
 *
 * Created by Haocxx
 * on 2018/11/28
 */
object LogUtil {

    /**
     * @param tag The log tag to print.
     * @param msg The log content to print.
     */
    fun d(tag : String, msg : String) {
        d(tag, msg, true)
    }

    /**
     * @param tag The log tag to print.
     * @param msg The log content to print.
     * @param isDebug To control log printer works under debug build type.
     */
    fun d(tag : String, msg : String, isDebug : Boolean) {
        if (isDebug) {
            Log.d(tag, msg)
        }
    }
}