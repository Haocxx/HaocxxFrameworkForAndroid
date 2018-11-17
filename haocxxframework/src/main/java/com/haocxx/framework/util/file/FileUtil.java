package com.haocxx.framework.util.file;

import android.text.TextUtils;

import java.io.File;

/**
 * Created by Haocxx
 * on 2018/8/29
 */
public class FileUtil {

    /**
     * Create directories to the given path string.
     *
     * @param path The path string of the directory.
     * @return True if success and false if failed.
     */
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

    /**
     * Create directories to the given file.
     *
     * @param file The file of the directory.
     * @return True if success and false if failed.
     */
    public static boolean makeDirs(File file) {
        if (!file.exists()) {
            if (file.isDirectory()) {
                return file.mkdirs();
            } else {
                return file.getParentFile().mkdirs();
            }
        }
        return true;
    }
}
