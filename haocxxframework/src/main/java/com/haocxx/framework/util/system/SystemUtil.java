package com.haocxx.framework.util.system;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by Haocxx 
 * on 2018/11/26
 */
public class SystemUtil {
    public static String getAndroidId (Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
