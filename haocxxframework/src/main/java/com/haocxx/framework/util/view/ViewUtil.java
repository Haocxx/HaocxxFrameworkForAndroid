package com.haocxx.framework.util.view;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Created by Haocxx
 * on 2018/12/11
 */
public class ViewUtil {

    public static void setRoundCorner(View view, final int radiusInPx) {
        if (view != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setOutlineProvider(new ViewOutlineProvider() {
                Rect selfRect = new Rect();
                int radius = radiusInPx;
                @Override
                public void getOutline(View view, Outline outline) {
                    selfRect.set(0, 0,
                            view.getMeasuredWidth(), view.getMeasuredHeight());
                    outline.setRoundRect(selfRect, radius);
                }
            });
            view.setClipToOutline(true);
        }
    }
}
