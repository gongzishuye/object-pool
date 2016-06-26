package com.initial.sy;

/**
 * @author chen lu
 * @date 2016/6/26
 * @time 16:07
 */
public interface ObjectPool<T> {

    T borrowObject();

    void returnObject(T object);
}
