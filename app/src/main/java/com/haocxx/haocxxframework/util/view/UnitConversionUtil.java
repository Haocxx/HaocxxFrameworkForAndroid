package com.haocxx.haocxxframework.util.view;

import android.content.Context;

/**
 * Created by Haocxx 
 * on 2018/10/28
 */
public class UnitConversionUtil {
    /**
     * Convert PX to DP.
     *
     * @param context Android context like Application or Activity.
     * @param pxValue PX value to be converted.
     * @return The DP value result of PX value.
     */
    public static int convertPxToDip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue /scale + 0.5f);
    }

    /**
     * Convert DP to PX.
     * @param context Android context like Application or Activity.
     * @param dipValue DP value to be converted.
     * @return The PX value result of DP value.
     */
    public static int convertDipToPx(Context context,float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    /**
     * Convert PX to SP.
     * @param context Android context like Application or Activity.
     * @param pxValue DX value to be converted.
     * @return The SP value result of PX value.
     */
    public static int convertPxToSp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue /fontScale + 0.5f);
    }

    /**
     * Convert SP to PX.
     * @param context Android context like Application or Activity.
     * @param spValue SP value to be converted.
     * @return The PX value result of SP value.
     */
    public static int convertSpToPx(Context context,float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5f);
    }
}
