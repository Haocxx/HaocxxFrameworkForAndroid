package com.haocxx.framework.util.media;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Add filter tool by matrix.
 *
 * Created by Haocxx
 * on 2018/11/29
 *
 * https://www.jianshu.com/p/9a44d04f39fc
 */
public class FilterUtil {

    /**
     * Get a {@link ColorMatrix} by a float array with matrix value.
     * @param src The matrix value, length should greater than 20.
     * @return The created ColorMatrix object.
     */
    public static ColorMatrix getColorMatrix(float[] src) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(src);
        return colorMatrix;
    }

    /**
     * A method invoked for adding a color-matrix filter effect to a bitmap.
     *
     * @param src The bitmap object shall add color-matrix filter.
     * @param colorMatrix The color-matrix to add.
     * @return The bitmap object with color-matrix filter.
     */
    public static Bitmap getMatrixBitmap(Bitmap src, ColorMatrix colorMatrix) {
        Bitmap bmp = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(src, 0, 0, paint);
        return bmp;
    }
}
