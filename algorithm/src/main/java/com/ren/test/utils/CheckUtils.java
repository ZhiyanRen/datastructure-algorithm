/*
 * Copyright (c) 2016-2019 法大大 All rights reserved.
 *
 *  https://www.fadada.com/
 *
 *  版权所有，侵权必究！
 */
package main.java.com.ren.test.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据验证工具类
 */
public class CheckUtils {


    /**
     * 判断引用类型数组是否为空，为空或 null 则返回 true，否则返回 false
     *
     * @param arr
     * @return
     */
    public static boolean isEmpty(Object[] arr) {
        return null == arr ? true : arr.length == 0 ? true : false;
    }

    /**
     * String... strings 为可变参数，如果 strings 中有任意一个字符串为空，则返回 false，否则返回 true。
     *
     * @param strings
     * @return
     */
    public static boolean isAnyEmpty(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (isBlank(strings[i]))
                return false;
        }
        return true;
    }

    /**
     * 判断 str1 字符串是否相等，相等则返回 true，否则返回 false。当其中一个是 null 时返回 false。
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        //短路或：||，只要有一个条件满足，即返回false
        //所以str1肯定不为null，str2可能为null
        if (null == str1 || null == str2)
            return false;
        //此时str2可能为null，但是String的重写equals参数允许为null（看源码）
        if (str1.equals(str2))
            return true;
        return false;
    }

    /**
     * 方法重载
     * 判断 n1 和 n2 的值是否相等，相等则返回 true，否则返回 false。当其中一个是 null 时返回 false。
     */
    public static boolean equals(Integer n1, Integer n2) {
        if (null == n1 || null == n2)
            return false;
        //Integer 的==是值比较
        return n1 == n2;
    }

    /**
     * 检查字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return (str == null || "null".equals(str) || "".equals(str) ? true : false);
    }

    /**
     * 将传入的null值改为空串
     *
     * @param obj
     * @return
     */
    public static String nullToString(Object obj) {
        if (obj == null || "".equals(obj.toString()) || "null".equals(obj.toString())) {
            obj = "";
        }
        return String.valueOf(obj).trim();
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if ("".equals(nullToString(str))) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 字符串转换为数字 不是数字的置为0
     *
     * @param str
     * @return
     */
    public static int stringToInteger(String str) {
        if (isNumeric(str)) {
            return Integer.parseInt(str);
        } else {
            return 0;
        }
    }

}
