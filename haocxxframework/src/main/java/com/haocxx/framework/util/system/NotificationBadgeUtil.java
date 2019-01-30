package com.haocxx.framework.util.system;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.haocxx.framework.annotation.Unreliable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * A util to show badge in launcher for variable phone models while using
 * system notification. It is not reliable for all the phone models.
 *
 * Refer to : https://developer.android.com/training/notify-user/badges#kotlin
 * Refer to : http://www.androidchina.net/4093.html
 * Refer to : https://github.com/Cedric-Xuan/SNSNotificationBadge
 *
 * Created by Haocxx
 * on 2019/1/30
 */
@Unreliable
public class NotificationBadgeUtil {
    public static final String TAG = "NotificationBadgeUtil";

    private static final String SYSTEM_XIAOMI = "XIAOMI";
    private static final String SYSTEM_SAMSUNG = "SAMSUNG";
    private static final String SYSTEM_HUAWEI_HONOR = "HONOR";
    private static final String SYSTEM_HUAWEI = "HUAWEI";
    private static final String SYSTEM_NOVA = "NOVA";
    private static final String SYSTEM_SONY = "SONY";
    private static final String SYSTEM_VIVO = "VIVO";
    private static final String SYSTEM_OPPO = "OPPO";
    private static final String SYSTEM_LG = "LG";
    private static final String SYSTEM_ZUK = "ZUK";
    private static final String SYSTEM_HTC = "HTC";

    private static boolean hasInit = false;
    private static String OSName = null;

    /**
     * The static method to show a badge while using notification.
     *
     * Google provide system API for badges works in launcher since Android O,
     * if SDK version is older than Android O, you can using variant phone custom
     * launcher badge ways like dealing in this method. But some specific phone
     * models seems don`t support system API even though API over Android O, like
     * my HUAWEI P9 Android O.
     *
     * @param context Context for notification dependence.
     * @param notification Notification object correspond to badge.
     * @param NOTIFICATION_ID The notification channel id.
     * @param num The count of badges.
     */
    public static void showBadge(Context context, Notification notification, int NOTIFICATION_ID, int num) {
        if (!hasInit) {
            init();
        }

        OSName = Build.BRAND.trim().toUpperCase();
        if (notification != null) {
            if (num < 0) num = 0;
            if (num > 99) num = 99;
            Log.e("system_name", OSName);
            if (OSName != null) {
                if (OSName.equals(SYSTEM_XIAOMI)) {
                    setBadgeOfXiaomi(context, notification, NOTIFICATION_ID, num);
                } else if (OSName.equals(SYSTEM_SAMSUNG) || OSName.equals(SYSTEM_LG)) {
                    setBadgeOfSamsung(context, notification, NOTIFICATION_ID, num);
                } else if (OSName.equals(SYSTEM_HUAWEI_HONOR) || OSName.equals(SYSTEM_HUAWEI)) {
                    setBadgeOfHuaWei(context, notification, NOTIFICATION_ID, num);
                } else if (OSName.equals(SYSTEM_SONY)) {
                    setBadgeOfSony(context, notification, NOTIFICATION_ID, num);
                } else if (OSName.equals(SYSTEM_VIVO)) {
                    setBadgeOfVIVO(context, notification, NOTIFICATION_ID, num);
                } else if (OSName.equals(SYSTEM_OPPO)) {
                    setBadgeOfOPPO(context, notification, NOTIFICATION_ID, num);
                } else if (OSName.equals(SYSTEM_ZUK)) {
                    setBadgeOfZUK(context, notification, NOTIFICATION_ID, num);
                } else if (OSName.equals(SYSTEM_HTC)) {
                    setBadgeOfHTC(context, notification, NOTIFICATION_ID, num);
                } else if (OSName.equals(SYSTEM_NOVA)) {
                    setBadgeOfNOVA(context, notification, NOTIFICATION_ID, num);
                } else {
                    setBadgeOfDefault(context, notification, NOTIFICATION_ID, num);
                }
            }
        }
    }

    private static void init() {
        OSName = android.os.Build.BRAND.trim().toUpperCase();
        hasInit = true;
    }

    private static void setBadgeOfXiaomi(final Context context, final Notification notification, final int NOTIFICATION_ID, final int num) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    Field field = notification.getClass().getDeclaredField("extraNotification");
                    Object extraNotification = field.get(notification);
                    Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
                    method.invoke(extraNotification, num);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Xiaomi" + " Badge error", "set Badge failed");
                }
                NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
                if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
                else notifyMgr.cancel(NOTIFICATION_ID);
            }
        }, 550);
    }

    private static void setBadgeOfSamsung(Context context, Notification notification, int NOTIFICATION_ID, int num) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", num);
            intent.putExtra("badge_count_package_name", context.getPackageName());
            intent.putExtra("badge_count_class_name", launcherClassName);
            context.sendBroadcast(intent);

            NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
            if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
            else notifyMgr.cancel(NOTIFICATION_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SAMSUNG" + " Badge error", "set Badge failed");
        }
    }

    /*
        Need permission for HUAWEI models:
        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="com.huawei.android.launcher.permission.CHANGE_BADGE" />
     */
    private static void setBadgeOfHuaWei(Context context, Notification notification, int NOTIFICATION_ID, int num) {
        try {
            Bundle localBundle = new Bundle();
            localBundle.putString("package", context.getPackageName());
            localBundle.putString("class", getLauncherClassName(context));
            localBundle.putInt("badgenumber", num);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, localBundle);

            NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
            if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
            else notifyMgr.cancel(NOTIFICATION_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HUAWEI" + " Badge error", "set Badge failed");
        }
    }

    private static void setBadgeOfSony(Context context, Notification notification, int NOTIFICATION_ID, int num) {
        String numString = "";
        String activityName = getLauncherClassName(context);
        if (activityName == null) {
            return;
        }
        Intent localIntent = new Intent();
        boolean isShow = true;
        if (num < 1) {
            numString = "";
            isShow = false;
        } else if (num > 99) {
            numString = "99";
        }

        try {
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", isShow);
            localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", activityName);
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", numString);
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());
            context.sendBroadcast(localIntent);

            NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
            if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
            else notifyMgr.cancel(NOTIFICATION_ID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SONY" + " Badge error", "set Badge failed");
        }
    }

    private static void setBadgeOfVIVO(Context context, Notification notification, int NOTIFICATION_ID, int num) {
        try {
            Intent localIntent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            localIntent.putExtra("packageName", context.getPackageName());
            localIntent.putExtra("className", getLauncherClassName(context));
            localIntent.putExtra("notificationNum", num);
            context.sendBroadcast(localIntent);

            NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
            if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
            else notifyMgr.cancel(NOTIFICATION_ID);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("VIVO" + " Badge error", "set Badge failed");
        }
    }

    private static void setBadgeOfOPPO(Context context, Notification notification, int NOTIFICATION_ID, int num) {
        try {
            if (num == 0) {
                num = -1;
            }
            Intent intent = new Intent("com.oppo.unsettledevent");
            intent.putExtra("pakeageName", context.getPackageName());
            intent.putExtra("number", num);
            intent.putExtra("upgradeNumber", num);
            if (canResolveBroadcast(context, intent)) {
                context.sendBroadcast(intent);
            } else {
                try {
                    Bundle extras = new Bundle();
                    extras.putInt("app_badge_count", num);
                    context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, extras);

                    NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
                    if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
                    else notifyMgr.cancel(NOTIFICATION_ID);
                } catch (Throwable th) {
                    Log.e("OPPO" + " Badge error", "unable to resolve intent: " + intent.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("OPPO" + " Badge error", "set Badge failed");
        }
    }

    private static void setBadgeOfZUK(Context context, Notification notification, int NOTIFICATION_ID, int num) {
        final Uri CONTENT_URI = Uri.parse("content://com.android.badge/badge");
        try {
            Bundle extra = new Bundle();
            extra.putInt("app_badge_count", num);
            context.getContentResolver().call(CONTENT_URI, "setAppBadgeCount", null, extra);

            NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
            if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
            else notifyMgr.cancel(NOTIFICATION_ID);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ZUK" + " Badge error", "set Badge failed");
        }

    }

    private static void setBadgeOfHTC(Context context, Notification notification, int NOTIFICATION_ID, int num) {

        try {
            Intent intent1 = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
            intent1.putExtra("com.htc.launcher.extra.COMPONENT", context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().flattenToShortString());
            intent1.putExtra("com.htc.launcher.extra.COUNT", num);

            Intent intent = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
            intent.putExtra("packagename", context.getPackageName());
            intent.putExtra("count", num);

            if (canResolveBroadcast(context, intent1) || canResolveBroadcast(context, intent)) {
                context.sendBroadcast(intent1);
                context.sendBroadcast(intent);
            } else {
                Log.e("HTC" + " Badge error", "unable to resolve intent: " + intent.toString());
            }

            NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
            if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
            else notifyMgr.cancel(NOTIFICATION_ID);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HTC" + " Badge error", "set Badge failed");
        }

    }

    private static void setBadgeOfNOVA(Context context, Notification notification, int NOTIFICATION_ID, int num) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("tag", context.getPackageName() + "/" + getLauncherClassName(context));
            contentValues.put("count", num);
            context.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), contentValues);

            NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
            if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
            else notifyMgr.cancel(NOTIFICATION_ID);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("NOVA" + " Badge error", "set Badge failed");
        }
    }

    private static void setBadgeOfDefault(Context context, Notification notification, int NOTIFICATION_ID, int num) {

        try {
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", num);
            intent.putExtra("badge_count_package_name", context.getPackageName());
            intent.putExtra("badge_count_class_name", getLauncherClassName(context));
            if (canResolveBroadcast(context, intent)) {
                context.sendBroadcast(intent);
            } else {
                Log.e("Default" + " Badge error", "unable to resolve intent: " + intent.toString());
            }

            NotificationManager notifyMgr = (NotificationManager) (context.getSystemService(NOTIFICATION_SERVICE));
            if (num != 0) notifyMgr.notify(NOTIFICATION_ID, notification);
            else notifyMgr.cancel(NOTIFICATION_ID);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Default" + " Badge error", "set Badge failed");
        }

    }

    private static String getLauncherClassName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setPackage(context.getPackageName());
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (info == null) {
            info = packageManager.resolveActivity(intent, 0);
        }
        return info.activityInfo.name;
    }

    private static boolean canResolveBroadcast(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> receivers = packageManager.queryBroadcastReceivers(intent, 0);
        return receivers != null && receivers.size() > 0;
    }
}
