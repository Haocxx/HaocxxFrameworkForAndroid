package com.haocxx.framework.constant.filter;

import android.graphics.ColorMatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haocxx
 * on 2018/11/29
 */
public class FilterColorMatrixConstant {
    // 曝光效果
    private static ColorMatrix matrix1 = new ColorMatrix(new float[]{ -1,0,0,0,255, 0,-1,0,0,255, 0,0,-1,0,255, 0,0,0,1,0, });

    // 美白效果
    private static ColorMatrix matrix2 = new ColorMatrix(new float[]{ 1.2f,0,0,0,0, 0,1.2f,0,0,0, 0,0,1.2f,0,0, 0,0,0,1.2f,0, });

    // 黑白图片
    private static ColorMatrix matrix3 = new ColorMatrix(new float[]{ 0.213f, 0.715f,0.072f,0,0, 0.213f, 0.715f,0.072f,0,0, 0.213f, 0.715f,0.072f,0,0, 0, 0, 0, 1f,0, });

    // 复古风格
    private static ColorMatrix matrix4 = new ColorMatrix(new float[]{ 1/2f,1/2f,1/2f,0,0, 1/3f,1/3f,1/3f,0,0, 1/4f,1/4f,1/4f,0,0, 0,0,0,1f,0, });

    // 二元风格
    private static ColorMatrix matrix5 = new ColorMatrix(new float[]{ 255f, 0, 0, 1, -255 * 128f, 0, 255f, 0, 1, -255 * 128f, 0, 0, 255f, 1, -255 * 128f, 0, 0, 0, 1, 0 });

    public static final List<ColorMatrix> COLOR_MATRIX_LIST = new ArrayList<>();

    static {
        COLOR_MATRIX_LIST.add(matrix1);
        COLOR_MATRIX_LIST.add(matrix2);
        COLOR_MATRIX_LIST.add(matrix3);
        COLOR_MATRIX_LIST.add(matrix4);
        COLOR_MATRIX_LIST.add(matrix5);
    }
}
