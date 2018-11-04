package com.haocxx.haocxxframework.util.media;

import android.graphics.Bitmap;

import com.haocxx.haocxxframework.util.file.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Haocxx 
 * on 2018/11/4
 */
public class BitmapUtil {

    /**
     * Save a bitmap object to file.
     *
     * @param bitmap The bitmap object to be saved.
     * @param path The path to save.
     * @param format Save format. JPG, PNG, etc.
     * @param quality The quality of saved image. Range 0 to 100. 0 meaning compress for
     *                small size, 100 meaning compress for max quality. Some formats, like
     *                PNG which is lossless, will ignore the quality setting
     * @return True if success, false if failed.
     */
    public static boolean saveBitmapToLocal(Bitmap bitmap, String path, Bitmap.CompressFormat format, int quality) {
        if(bitmap == null) {
            return false;
        }
        File file = new File(path);
        FileUtil.makeDirs(file);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return bitmap.compress(format, quality, outputStream);
    }
}
