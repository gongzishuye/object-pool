package com.initial.sy;

/**
 * @author chen lu
 * @date 2016/6/26
 * @time 16:15
 */
public interface ObjectFactory<T> {

    T makeObject ();

    void destroyObject(T object);
}
