package com.haocxx.haocxxframework.module.haocxxbank;

import android.support.annotation.Nullable;
import android.util.SparseArray;

import java.lang.ref.WeakReference;

/**
 * Object storage function like a bank. You can save an object to a bank by method
 * {@link #put(Object)} and get a key, then get it by method {@link #get(int)}
 * using the key.
 *
 * Created by Haocxx 
 * on 2018/11/4
 */
public class HaocxxBank {
    private static HaocxxBank sHaocxxBank;
    private SparseArray<WeakReference<Object>> mBankMap;

    /**
     * Get a default HaocxxBank object. It`s thread-safe.
     *
     * @return The default HaocxxBank object.
     */
    public static HaocxxBank getDefault() {
        if (sHaocxxBank == null) {
            synchronized (HaocxxBank.class) {
                if (sHaocxxBank == null) {
                    sHaocxxBank = new HaocxxBank();
                }
            }
        }
        return sHaocxxBank;
    }

    public HaocxxBank() {
        mBankMap = new SparseArray<>();
    }

    /**
     * Put an object to the bank pool. It will be saved in a {@link WeakReference},
     * witch means the object to be saved should have other strong reference, or if
     * might be cleaned by the Java GC.
     *
     * @param goods The object to be saved.
     * @return The key for the goods has been saved.
     */
    public synchronized int put(Object goods) {
        int key = getKey();
        while (mBankMap.get(key) != null) {
            key = getKey();
        }
        mBankMap.put(key, new WeakReference<>(goods));
        return key;
    }

    /**
     * Get the goods in bank pool by key. If no object is match the key, or object
     * has been cleaned by Java GC, null will be returned.
     *
     * @param key The key of a object in bank pool.
     * @return The object in the pool witch match the key.
     */
    @Nullable
    public synchronized Object get(int key) {
        WeakReference weakReference = mBankMap.get(key);
        if (weakReference == null) {
            return null;
        } else {
            Object goods = weakReference.get();
            if (goods == null) {
                mBankMap.remove(key);
                return null;
            } else {
                return goods;
            }
        }
    }

    private int getKey() {
        return (int) (Math.random() * 10000000);
    }
}
