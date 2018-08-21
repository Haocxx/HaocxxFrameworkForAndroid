package com.haocxx.haocxxframework.util.view;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Haocxx
 * on 2018/8/21
 */
public class TextViewUtil {

    /**
     * Make a {@link TextView} text content bold, or normal.
     *
     * @param textView {@link TextView} instance.
     * @param makeTextViewBold Make textView bold if true, normal if false.
     */
    public static void setBold(TextView textView ,boolean makeTextViewBold) {
        if(textView == null) {
            throw new NullPointerException("textView must not be null");
        }
        if(makeTextViewBold) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }
}
