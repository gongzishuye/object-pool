package com.initial.sy;

import com.initial.sy.implement.CommonObjectPool;
import org.junit.Test;

/**
 * @author chen lu
 * @date 2016/6/26
 * @time 16:36
 */
public class CommonObjectPoolTest {

    @Test
    public void commonObjectPoolTest(){
        ObjectFactory<String> objectFactory = new ObjectFactory<String>() {
            public String makeObject() {
                return "success";
            }

            public void destroyObject(String object) {
                System.out.println(object);
            }
        };
        CommonObjectPool<String> commonObjectPool = new CommonObjectPool<String>(objectFactory);
        System.out.println(commonObjectPool.borrowObject());
    }
}
