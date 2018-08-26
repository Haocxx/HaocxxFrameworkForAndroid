package com.haocxx.haocxxframework.manager.base;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Haocxx
 * on 2018/8/21
 */
public class BaseInstanceManager<T> {
    protected List<T> mInstanceList;

    public BaseInstanceManager() {
        mInstanceList = new LinkedList<>();
    }

    public int getInstanceListSize() {
        return mInstanceList.size();
    }

    public T get(int index) {
        int size = getInstanceListSize();
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index out of bounds in list");
        }
        return mInstanceList.get(index);
    }

    public T getTopInstanceInList() {
        int size = getInstanceListSize();
        if(size != 0) {
            return mInstanceList.get(size - 1);
        } else {
            return null;
        }

    }

    public T getBottomInstanceInList() {
        int size = getInstanceListSize();
        if(size != 0) {
            return mInstanceList.get(0);
        } else {
            return null;
        }
    }

    public void add(T instance) {
        mInstanceList.add(instance);
    }

    public void add(int index, T instance) {
        mInstanceList.add(index, instance);
    }

    public void remove(int index) {
        mInstanceList.remove(index);
    }

    public void clear() {
        mInstanceList.clear();
    }

    public void clearNullInstance() {
        int size = getInstanceListSize();
        for(int index = 0; index < size;) {
            if(get(index) == null) {
                remove(index);
            }
        }
    }
}
