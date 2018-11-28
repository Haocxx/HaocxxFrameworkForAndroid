package com.haocxx.framework.system;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 *  A listener to the soft keyboard showing/hidden state. It works by listen to
 *  the height change of the view. Actually it`s not reliable, cause some other
 *  view height change situations can also activate this listener callback.
 *
 *  Created by Haocxx 
 *  on 2018/9/22
 *  
 *  https://blog.csdn.net/u011181222/article/details/52043001
 */
public class SoftKeyBoardListener {
    private View mRootView;
    private int rootViewVisibleHeight;
    private OnSoftKeyBoardChangeListener mOnSoftKeyBoardChangeListener;

    public SoftKeyBoardListener(Activity activity) {
        mRootView = activity.getWindow().getDecorView();

        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mRootView.getWindowVisibleDisplayFrame(r);

                int visibleHeight = r.height();
                if (rootViewVisibleHeight == 0) {
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                if (rootViewVisibleHeight == visibleHeight) {
                    return;
                }

                if (rootViewVisibleHeight - visibleHeight > 200) {
                    if (mOnSoftKeyBoardChangeListener != null) {
                        mOnSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                if (visibleHeight - rootViewVisibleHeight > 200) {
                    if (mOnSoftKeyBoardChangeListener != null) {
                        mOnSoftKeyBoardChangeListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

            }
        });
    }

    private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.mOnSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    /**
     * The callback of soft keyboard state change listener. {@link #keyBoardHide(int)} will be
     * invoked when keyboard turn to hidden, and {@link #keyBoardShow(int)} will be invoked when
     * keyboard turn to showing.
     */
    public interface OnSoftKeyBoardChangeListener {
        void keyBoardShow(int height);

        void keyBoardHide(int height);
    }

    public static void setListener(Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        SoftKeyBoardListener softKeyBoardListener = new SoftKeyBoardListener(activity);
        softKeyBoardListener.setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
    }
}
