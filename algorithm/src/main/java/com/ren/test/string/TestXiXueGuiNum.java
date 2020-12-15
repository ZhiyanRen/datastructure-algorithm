package main.java.com.ren.test.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program: datastructure-algorithm
 * @Date: 2019/12/14 8:42
 * @Author: Mrs.Ren
 * @Description: 吸血鬼数字练习
 *  
 */
public class TestXiXueGuiNum {

    @Test
    public void testOne() {
        long start = System.currentTimeMillis();
        //结果去重：避免出现 1395=15*93 和 1395=93*15
        HashSet<Integer> set = new HashSet<>();
        //分析题知：两个乘数只能是两位数
        for (int num1 = 11; num1 <= 99; num1++) {
            for (int num2 = 99; num2 >= 11; num2--) {
                Integer product = num1 * num2;
                String result = String.valueOf(product);
                // 结果长度不为4就不进行后续操作
                if (result.length() == 4) {
                    char[] resultArr = result.toCharArray();
                    char[] numArr = (String.valueOf(num1) + String.valueOf(num2)).toCharArray();
                    Arrays.sort(resultArr);
                    Arrays.sort(numArr);
                    //看源码：Arrays.equals 实质比较的是:①两数组长度相同 ②两数组对应索引位置元素相同，同时满足返回true
                    //所以使用的时候一般要先将数组排序后比较，即实现判断两数组元素长度相等且元素完全相同
                    if (Arrays.equals(resultArr, numArr)) {
                        if (set.add(product))
                            System.out.println(product + "=" + num1 + "*" + num2);
                    }
                }
            }
        }
        System.out.println("共几组：" + set.size());
        long end = System.currentTimeMillis();
        System.out.println(end - start);//15
    }

    /**
     * 网上找的优化方式:
     * 比较两个数组相等方式值得学习：
     * ① 数组长度相等
     * ② 元素相同
     */
    @Test
    public void testTwo() {
        long start = System.currentTimeMillis();
        int sum = 0;
        int[] startDigit = new int[4];
        int[] productDigit = new int[4];
        //优化点一：num2直接从num1开始，避免重复 1395=15*93 1395=93*15
        for (int num1 = 10; num1 <= 99; num1++)
            for (int num2 = num1; num2 <= 99; num2++) {
                //这个是吸血鬼数字满足的公式 x·y == x+y (mod 9)
                if ((num1 * num2) % 9 != (num1 + num2) % 9)
                    continue;
                int product = num1 * num2;
                startDigit[0] = num1 / 10;//num1的十位上数字
                startDigit[1] = num1 % 10;//num1的个位上数字
                startDigit[2] = num2 / 10;//num2的十位上数字
                startDigit[3] = num2 % 10;//num2的个位上数字
                //获取结果的个、十、百、千位上的数字
                productDigit[0] = product / 1000;
                productDigit[1] = (product % 1000) / 100;
                productDigit[2] = product % 1000 % 100 / 10;
                productDigit[3] = product % 1000 % 100 % 10;
                //记录两个数组相等的元素个数
                int count = 0;
                //优化点二：遍历数组，不进行排序再比较
                for (int x = 0; x < 4; x++)
                    for (int y = 0; y < 4; y++) {
                        if (productDigit[x] == startDigit[y]) {
                            count++;
                            //给相关索引位置重新赋不同的负数，表示已经被比较过
                            productDigit[x] = -1;
                            startDigit[y] = -2;
                            if (count == 4) {
                                System.out.println("第" + sum + "组: " + num1 + " * " + num2 + " : " + product);
                                sum++;
                            }
                        }
                    }
            }
        System.out.println("共几组" + sum);
        long end = System.currentTimeMillis();
        System.out.println(end - start);//0
    }

    public static void main(String[] args) {
        System.out.println(1395 % 9);
        System.out.println(108 % 9);
    }

}
