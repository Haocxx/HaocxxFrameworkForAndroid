package com.haocxx.framework.util.media;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

/**
 * Created by Haocxx
 * on 18-9-3
 */
public class VideoUtil {

    /**
     * Cut out the first frame of a video to bitmap.
     *
     * @param path The path of video to cut frame out.
     * @return The first frame bitmap of the video.
     */
    public static Bitmap getFirstFrameBitmapFromVideo(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        return media.getFrameAtTime();
    }
}
