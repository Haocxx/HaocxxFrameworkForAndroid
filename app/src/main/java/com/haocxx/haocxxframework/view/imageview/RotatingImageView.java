package com.haocxx.haocxxframework.view.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by Haocxx
 * on 18-9-18
 */
@SuppressLint("AppCompatCustomView")
public class RotatingImageView extends ImageView {
    private RotateAnimation mAnimation;

    public RotatingImageView(Context context) {
        this(context, null);
    }

    public RotatingImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotatingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int magnify = 10000;
        int toDegrees = 360;
        int duration = 500;
        toDegrees *= magnify;
        duration *= magnify;
        mAnimation = new RotateAnimation(0,toDegrees,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mAnimation.setDuration(duration);
        LinearInterpolator lir = new LinearInterpolator();
        mAnimation.setInterpolator(lir);
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.RESTART);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(mAnimation != null) {
            if(visibility == View.GONE) {
                clearAnimation();
            } else if(visibility == View.VISIBLE) {
                clearAnimation();
                startAnimation(mAnimation);
            } else if(visibility == View.INVISIBLE) {
                clearAnimation();
            }
        }
    }
}
