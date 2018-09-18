package com.haocxx.haocxxframework.util.file;

import android.text.TextUtils;

import java.io.File;

/**
 * Created by Haocxx
 * on 2018/8/29
 */
public class FileUtil {

    public static boolean makeDirs(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            return true;
        }
        return dir.mkdirs();
    }

    public static boolean makeDirs(File file) {
        if (!file.exists()) {
            if (file.isDirectory()) {
                if(!file.mkdirs()) {
                    return false;
                }
            } else {
                if(!file.getParentFile().mkdirs()) {
                    return false;
                }
            }
        }
        return true;
    }
}
