package com.brady.order.utils;

/**
 * @description:
 * @author: brady.si
 * @create: 2020-12-07 23:05
 */
public class DateUtil {
    public static int getCurrentSeconds(){
        return (int) System.currentTimeMillis() / 1000;
    }
}
