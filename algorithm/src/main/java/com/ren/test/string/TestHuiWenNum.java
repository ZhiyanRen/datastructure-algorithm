package main.java.com.ren.test.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * @program: datastructure-algorithm
 * @Date: 2019/12/14 15:03
 * @Author: Mrs.Ren
 * @Description:判断数字是否是回文数字，本质判断字符串反转后是否和原来字符串相同 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *  
 */
public class TestHuiWenNum {

    @Test
    public void test() {
        //Integer num = null; //此时调用方法报空指针异常，因为x = num,num.intValue()有个自动拆箱，所以num为空会报空指针异常
        int num = 10;
        System.out.println("数字" + num + "是回文数字：" + isPalindrome(num));
        //或者使用断言判断测试结果
        Assert.assertEquals(false, isPalindrome(num));
    }

    /**
     * 判断数字是否是回文数字
     *
     * @param x
     * @return
     */
    boolean isPalindrome(int x) {
        String originalStr = String.valueOf(x);
        //直接调用StringBuilder的reverse进行字符串反转
        String reverseStr = StringsReverse.getStrReverse(originalStr);
        return originalStr.equals(reverseStr);
    }


}
