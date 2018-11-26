package com.haocxx.framework.util.system;

import android.content.Context;

/**
 * Created by Haocxx 
 * on 2018/11/26
 */
public class SystemUtil {
    public static String getAndroidId (Context context) {
        return android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
    }
}
