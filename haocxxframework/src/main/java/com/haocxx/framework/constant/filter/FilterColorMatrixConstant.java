package com.haocxx.framework.constant.filter;

import android.graphics.ColorMatrix;

/**
 * Created by Haocxx
 * on 2018/11/29
 */
public class FilterColorMatrixConstant {
    //曝光效果
    public static ColorMatrix matrix = new ColorMatrix(new float[]{ -1,0,0,0,255, 0,-1,0,0,255, 0,0,-1,0,255, 0,0,0,1,0, });

    //美白效果
    public static ColorMatrix matrix2 = new ColorMatrix(new float[]{ 1.2f,0,0,0,0, 0,1.2f,0,0,0, 0,0,1.2f,0,0, 0,0,0,1.2f,0, });

    //黑白图片
    public static ColorMatrix matrix3 = new ColorMatrix(new float[]{ 0.213f, 0.715f,0.072f,0,0, 0.213f, 0.715f,0.072f,0,0, 0.213f, 0.715f,0.072f,0,0, 0, 0, 0, 1f,0, });

    //复古风格
    public static ColorMatrix matrix4 = new ColorMatrix(new float[]{ 1/2f,1/2f,1/2f,0,0, 1/3f,1/3f,1/3f,0,0, 1/4f,1/4f,1/4f,0,0, 0,0,0,1f,0, });
}
