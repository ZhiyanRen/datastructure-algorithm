package main.java.com.ren.test.array;

import java.util.Arrays;

/**
 * @program: datastructure-algorithm
 * @Date: 2019/12/14 21:09
 * @Author: Mrs.Ren
 * @Description:快排  
 */
public class ArrayQuickSorted {
    /**
     * 快排：时间复杂度：O(nlogn)
     * <p>
     * 基本思想：取一个基准位置数字，用其他位置的数字和基准数进行对比
     * 如果比基准数大，则放到基准数的右边，如果比基准数小，则放到基准数的左边，
     * 然后再将两部分分别使用递归进行比较，直到排好序
     *
     * @param arr   排序数组
     * @param left  最小数组索引：0
     * @param right 最大数组索引：arr.length - 1
     * @return
     */
    public static int[] quickSort(int[] arr, int left, int right) {
        long start = System.currentTimeMillis();
        if (left < right) {
            //移动的指向小于key元素的指针下标
            int low = left;
            //移动的指向大于key元素的指针下标
            int high = right;
            //基准元素
            int key = arr[left];

            while (low < high) {
                //将high指针递减找小于基准元素key的值
                while (low < high && arr[high] >= key) {
                    high--;
                }
                //找到小的值赋值给low指向的值
                arr[low] = arr[high];
                //接着移动low指针，进行递增找大于基准元素key的值
                while (low < high && arr[low] <= key) {
                    low++;
                }
                //找到大于基准元素key的值赋值给high
                arr[high] = arr[low];
            }
            //直到退出循环，即low = high的时候，即为基准元素的位置
            arr[low] = key;
            //递归处理所有比标准数小的数字
            quickSort(arr, left, low);
            //递归处理所有比标准数大的数字
            quickSort(arr, low + 1, right);
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] num = {1, 6, 2, 8, 9, 20, 54, 23, 20, 56, 35};
        System.out.println("快排结果：" + Arrays.toString(quickSort(num, 0, num.length - 1)));
    }
}
