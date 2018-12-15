package com.haocxx.framework.util.system;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Haocxx
 * on 2018/12/14
 */
public final class ScreenUtil {

    static int mScreenWidth;
    static int mScreenHeight;
    static float mScreenDensity = 2.0f;
    static int mDensityDpi;

    static boolean isInited = false;

    public synchronized static void init(Activity activity) {
        if (activity == null) {
            return;
        }

        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;  // 屏幕宽度（像素）
        mScreenHeight = metric.heightPixels;  // 屏幕高度（像素）
        mScreenDensity = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        mDensityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        isInited = true;
    }

    public synchronized static boolean hasInited() {
        return isInited;
    }

    public static int getScreenWidth() {
        if (!isInited) {
            DisplayMetrics metric = Resources.getSystem().getDisplayMetrics();
            if (metric != null) {
                return metric.widthPixels;
            }
        }
        return mScreenWidth;
    }

    public static int getScreenHeight() {
        if (!isInited) {
            DisplayMetrics metric = Resources.getSystem().getDisplayMetrics();
            if (metric != null) {
                return metric.heightPixels;
            }
        }
        return mScreenHeight;
    }

    public static float getScreenDensity() {
        if (!isInited) {
            DisplayMetrics metric = Resources.getSystem().getDisplayMetrics();
            if (metric != null) {
                return metric.density;
            }
        }
        return mScreenDensity;
    }

    public static float getScreenDensityDpi() {
        if (!isInited) {
            DisplayMetrics metric = Resources.getSystem().getDisplayMetrics();
            if (metric != null) {
                return metric.densityDpi;
            }
        }
        return mDensityDpi;
    }

    public static int[] getScreenSize(Context context) {
        if (context == null) {
            return null;
        }

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            return null;
        }

        Display display = windowManager.getDefaultDisplay();
        if (display == null) {
            return null;
        }

        int[] size = new int[2];
        if (Build.VERSION.SDK_INT > 13) {
            Point point = new Point();
            display.getSize(point);
            size[0] = point.x;
            size[1] = point.y;
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            size[0] = metrics.widthPixels;
            size[1] = metrics.heightPixels;
        }

        return size;
    }
}