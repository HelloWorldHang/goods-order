package com.brady.order.utils;

import org.springframework.beans.BeanUtils;

/**
 * 通用类型转化转换工具类
 * @author Administrator
 *
 */
public class BeanConvertUtils{

    public static final <Target>Target copyProperties(Object source,Class<Target> targetClass){
        try {
            if(source==null || targetClass==null){
                return null;
            }
            Target doInstance = targetClass.newInstance();
            BeanUtils.copyProperties(source, doInstance);
            return doInstance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
