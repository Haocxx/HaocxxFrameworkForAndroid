package com.haocxx.haocxxframework.util.media;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

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
}
