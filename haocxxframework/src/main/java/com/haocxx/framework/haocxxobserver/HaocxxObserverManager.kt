package com.haocxx.framework.haocxxobserver

import com.haocxx.framework.haocxxobserver.observer.BaseObserver

/**
 * The middleware manager for observe logic.
 *
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

    /**
     *  Register an observer in the observer set. It`s a thread safe method.
     */
    fun regist(observer : BaseObserver) {
        synchronized(mObserverSet) {
            mObserverSet.add(observer)
        }
    }

    /**
     * Unregister an observer from the observer set. It`s a thread safe method.
     * It should be invoked to prevent memory leak.
     */
    fun unregist(observer : BaseObserver) {
        synchronized(mObserverSet) {
            mObserverSet.remove(observer)
        }
    }

    /**
     * Do notify to all the observers in the set. It`s a thread safe method.
     */
    fun doNotify() {
        synchronized(mObserverSet) {
            for (observer in mObserverSet) {
                observer.onNotify()
            }
        }
    }
}