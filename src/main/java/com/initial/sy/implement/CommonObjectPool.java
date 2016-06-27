package com.initial.sy.implement;

import com.initial.sy.ObjectFactory;
import com.initial.sy.ObjectPool;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author chen lu
 * @date 2016/6/26
 * @time 16:07
 */
public class CommonObjectPool<T> implements ObjectPool<T> {

    /*--------------------参数-------------------------*/
    private long maxWaitTime;

    private int idleObject;

    private LinkedBlockingDeque<T> allocationQueue = new LinkedBlockingDeque<T>();

    private ObjectFactory factory = null;

    /*--------------------配置类------------------------*/
    static class Config{

        private static long MAX_WAIT_TIME = 100l;

        private static int IDLE_OBJECT = 10;

    }

    /*--------------------构造方法-----------------------*/
    public CommonObjectPool(ObjectFactory factory) {
        this(factory, Config.MAX_WAIT_TIME, Config.IDLE_OBJECT);
    }

    public CommonObjectPool(ObjectFactory factory, long maxWaitTime, int idleObject) {
        this.factory = factory;
        this.maxWaitTime = maxWaitTime;
        this.idleObject = idleObject;
        this.fillQueue();
    }

    public T borrowObject() {
        T borrowedObj = allocationQueue.poll();
        if(borrowedObj != null) {
            return borrowedObj;
        } else {
            return (T)factory.makeObject();
        }
    }

    public void returnObject(T object) {
        if(object == null) {
            return;
        }
        if(!allocationQueue.offer(object)) {
            factory.destroyObject(object);
        }
    }

    private void fillQueue() {
        for (int i = 0; i < maxWaitTime; i++) {
            allocationQueue.add((T) factory.makeObject());
        }
    }

}
