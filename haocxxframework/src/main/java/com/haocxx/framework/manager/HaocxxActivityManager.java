package com.haocxx.framework.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by Haocxx
 * on 2019/2/15
 */
public class HaocxxActivityManager {
    private Application mApplication;
    private Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

    public void regist(Application application) {
        mApplication = application;
        mActivityLifecycleCallbacks = new HaocxxActivityLifecycleCallbacks();
        application.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
    }

    private static class HaocxxActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
