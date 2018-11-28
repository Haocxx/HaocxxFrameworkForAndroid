package com.haocxx.framework.util.system;

import android.content.Context;
import android.provider.Settings;

import java.util.UUID;

/**
 * Created by Haocxx 
 * on 2018/11/26
 */
public class SecureUtil {

    public static String getAndroidId (Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
