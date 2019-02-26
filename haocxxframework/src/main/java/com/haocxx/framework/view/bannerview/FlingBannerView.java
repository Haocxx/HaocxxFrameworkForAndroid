package com.haocxx.framework.view.bannerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.haocxx.framework.util.system.HandlerUtil;

/**
 * Created by Haocxx
 * on 2019/2/26
 */
public class FlingBannerView extends ViewPager {

    private static final int AUTO_FLING_DELAY = 3000;

    private Runnable mAutoFlingRunnable = new Runnable() {
        @Override
        public void run() {
            FlingBannerView.this.setCurrentItem(FlingBannerView.this.getCurrentItem() + 1, true);
            FlingBannerView.this.postAutoFlingRunnable();
        }
    };

    public FlingBannerView(Context context) {
        this(context, null);
    }

    public FlingBannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        postAutoFlingRunnable();
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0) {
                    HandlerUtil.getUIHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            setCurrentPageSilence(position);
                        }
                    });
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setCurrentPageSilence(int position) {
        if (position == 0 && getAdapter() != null) {
            setCurrentItem(getAdapter().getCount() - 2, false);
        } else if (getAdapter() != null && position == getAdapter().getCount() - 1) {
            setCurrentItem(1, false);
        }
    }

    private void postAutoFlingRunnable() {
        if (mAutoFlingRunnable != null) {
            HandlerUtil.getUIHandler().postDelayed(mAutoFlingRunnable, AUTO_FLING_DELAY);
        }
    }

    private void cancelAutoFlingRunnable() {
        if (mAutoFlingRunnable != null) {
            HandlerUtil.getUIHandler().removeCallbacks(mAutoFlingRunnable);
        }
    }

    public void release() {
        cancelAutoFlingRunnable();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cancelAutoFlingRunnable();
                break;
            case MotionEvent.ACTION_UP:
                postAutoFlingRunnable();
                break;
            case MotionEvent.ACTION_CANCEL:
                postAutoFlingRunnable();
                break;
        }
        return super.onTouchEvent(ev);
    }
}
