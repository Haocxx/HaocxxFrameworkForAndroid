package com.haocxx.framework.haocxxobserver

import com.haocxx.framework.haocxxobserver.observer.BaseObserver

/**
 * Created by Haocxx 
 * on 2018/11/18
 */
class HaocxxObserverManager {
    companion object {
        private val sHaocxxObserverManager = HaocxxObserverManager()

        fun getDefault() : HaocxxObserverManager {
            return sHaocxxObserverManager
        }
    }

    private val mObserverSet = HashSet<BaseObserver>()

    fun regist(observer : BaseObserver) {
        synchronized(mObserverSet) {
            mObserverSet.add(observer)
        }
    }

    fun unregist(observer : BaseObserver) {
        synchronized(mObserverSet) {
            mObserverSet.remove(observer)
        }
    }

    fun doNotify() {
        synchronized(mObserverSet) {
            for (observer in mObserverSet) {
                observer.onNotify()
            }
        }
    }
}