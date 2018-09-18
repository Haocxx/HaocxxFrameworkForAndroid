package com.haocxx.haocxxframework.util.media;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

import com.haocxx.haocxxframework.util.file.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Haocxx
 * on 18-9-3
 */
public class VideoUtil {
    public static Bitmap getFirstFrameBitmapFromVidep(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        Bitmap bitmap = media.getFrameAtTime();
        return bitmap;
    }

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
