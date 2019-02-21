package com.haocxx.framework.util.system;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Haocxx
 * on 2019/2/21
 */
public class HandlerUtil {
    private static Handler mUIHandler;

    /**
     *  Get UI Handler. It`s thread-safe.
     * @return UI Handler
     */
    public static Handler getUIHandler() {
        if (mUIHandler == null) {
            synchronized (HandlerUtil.class) {
                if (mUIHandler == null) {
                    mUIHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return mUIHandler;
    }
}
