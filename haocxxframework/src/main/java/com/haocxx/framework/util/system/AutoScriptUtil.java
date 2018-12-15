package com.haocxx.framework.util.system;

import java.io.IOException;

/**
 * Created by Haocxx
 * on 2018/12/14
 *
 * Refer to https://blog.csdn.net/slaron/article/details/78294833
 */
public class AutoScriptUtil {

    public static void doTapByPosition(final int x, final int y) {
        String[] order = { "input", "tap", "" + x, "" + y };
        executeOrderByProcessBuilder(order);
    }

    public static void doTapByRatio(final double ratioX, final double ratioY) {
        int x = (int) (ScreenUtil.getScreenWidth() * ratioX);
        int y = (int) (ScreenUtil.getScreenHeight() * ratioY);
        doTapByPosition(x, y);
    }

    public static void doSlideByPosition(final double startX, final double startY, final double endX, final double endY) {
        String order[] = {"input", "swipe", "" + startX, "" + startY, "" + endX, "" + endY};
        executeOrderByProcessBuilder(order);
    }

    public static void doSlideByRatio(final double startRatioX, final double startRatioY, final double endRatioX, final double endRatioY) {
        int startX = (int) (ScreenUtil.getScreenWidth() * startRatioX);
        int startY = (int) (ScreenUtil.getScreenHeight() * startRatioY);
        int endX = (int) (ScreenUtil.getScreenWidth() * endRatioX);
        int endY = (int) (ScreenUtil.getScreenHeight() * endRatioY);
        doSlideByPosition(startX, startY, endX, endY);
    }

    public static void executeOrderByProcessBuilder(String order[]) {
        try {
            new ProcessBuilder(order).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
