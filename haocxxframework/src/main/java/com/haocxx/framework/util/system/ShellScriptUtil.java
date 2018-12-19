package com.haocxx.framework.util.system;

import com.haocxx.framework.annotation.NotWork;

import java.io.IOException;

/**
 * A auto-run adb shell script util.
 *
 * Created by Haocxx
 * on 2018/12/14
 *
 * Refer to https://blog.csdn.net/slaron/article/details/78294833
 */
@NotWork
public class ShellScriptUtil {

    /**
     * Do a click on the screen by a specific position.
     *
     * @param x The X px position in the screen.
     * @param y The Y px position in the screen.
     */
    public static void doTapByPosition(final int x, final int y) {
        String[] order = { "input", "tap", "" + x, "" + y };
        executeOrderByProcessBuilder(order);
    }

    /**
     * Do a click on the screen by a X ratio and Y ratio in screen.
     *
     * @param ratioX The X ratio in screen.
     * @param ratioY The Y ratio in screen.
     */
    public static void doTapByRatio(final double ratioX, final double ratioY) {
        int x = (int) (ScreenUtil.getScreenWidth() * ratioX);
        int y = (int) (ScreenUtil.getScreenHeight() * ratioY);
        doTapByPosition(x, y);
    }

    /**
     * Do a swipe gesture in the screen by specific position.
     *
     * @param startX The start X position.
     * @param startY The start Y position.
     * @param endX The enn X position.
     * @param endY The end Y position.
     * @param duration The swipe duration.
     */
    public static void doSlideByPosition(final int startX, final int startY, final int endX, final int endY, final int duration) {
        String order[] = {"input", "swipe", "" + startX, "" + startY, "" + endX, "" + endY, "" + duration};
        executeOrderByProcessBuilder(order);
    }

    /**
     * Do a swipe gesture in the screen by position ratio in screen.
     *
     * @param startRatioX The start X ratio in screen.
     * @param startRatioY The start Y ratio in screen.
     * @param endRatioX The end X ratio in screen.
     * @param endRatioY The end Y ratio in screen.
     * @param duration The swipe duration.
     */
    public static void doSlideByRatio(final double startRatioX, final double startRatioY, final double endRatioX, final double endRatioY, final int duration) {
        int startX = (int) (ScreenUtil.getScreenWidth() * startRatioX);
        int startY = (int) (ScreenUtil.getScreenHeight() * startRatioY);
        int endX = (int) (ScreenUtil.getScreenWidth() * endRatioX);
        int endY = (int) (ScreenUtil.getScreenHeight() * endRatioY);
        doSlideByPosition(startX, startY, endX, endY, duration);
    }

    /**
     * Execute adb shell command by {@link ProcessBuilder}.
     *
     * @param order The command input append in string array.
     */
    public static void executeOrderByProcessBuilder(String order[]) {
        try {
            new ProcessBuilder(order).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
