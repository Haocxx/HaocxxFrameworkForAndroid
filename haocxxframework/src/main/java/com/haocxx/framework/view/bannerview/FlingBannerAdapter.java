package com.haocxx.framework.view.bannerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Haocxx
 * on 2019/2/26
 */
public abstract class FlingBannerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ItemBean> mData;

    public FlingBannerAdapter(Context context, List<ItemBean> data) {
        mContext = context;
        setData(data);
    }

    public void setData(List<ItemBean> data) {
        mData = data;
        if (mData != null) {
            int size = mData.size();
            if (size > 0) {
                ItemBean firstItem = mData.get(0);
                ItemBean lastItem = mData.get(size - 1);
                mData.add(0, lastItem);
                mData.add(firstItem);
            }
        }
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = View.inflate(mContext, R.layout.item_adv_banner ,null);
//        SimpleDraweeView imageView = view.findViewById(R.id.image_view);
//        if (mData != null && imageView != null) {
//            imageView.setImageURI(mData.get(position).iconPath);
//        }
//        container.addView(view);
//        return view;
//    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    public static class ItemBean {
        public String iconPath;

        public ItemBean(String iconPath) {
            this.iconPath = iconPath;
        }
    }

}
