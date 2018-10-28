package com.haocxx.haocxxframework.util.view;

import android.content.Context;

/**
 * Created by Haocxx 
 * on 2018/10/28
 */
public class UnitConversionUtil {
    /**
     * 将px值转换为dp值，保证尺寸不便。
     * @param context
     * @param pxValue
     * @return
     */
    public static int convertPxToDip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue /scale + 0.5f);
    }

    /**
     * 将dp值转换为px值，保持尺寸大小不变
     * @param context
     * @param dipValue
     * @return
     */
    public static int convertDipToPx(Context context,float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为dp值，保证尺寸不便。
     * @param context
     * @param pxValue
     * @return
     */
    public static int convertPxToSp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue /fontScale + 0.5f);
    }

    /**
     * 将dp值转换为px值，保持尺寸大小不变
     * @param context
     * @param spValue
     * @return
     */
    public static int convertSpToPx(Context context,float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5f);
    }
}
