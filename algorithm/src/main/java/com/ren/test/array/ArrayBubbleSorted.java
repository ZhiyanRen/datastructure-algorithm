package main.java.com.ren.test.array;

/**
 * @program: datastructure-algorithm
 * @Date: 2019/12/14 21:08
 * @Author: Mrs.Ren
 * @Description:冒泡排序  
 */
public class ArrayBubbleSorted {
    /**
     * 冒泡排序：升序排序 （时间复杂度O(n^2)）
     * 比较相邻的两个元素，如果前面的比后面的大，则交换两个元素；
     * 对每每相邻的元素都进行这样的比较操作，从开始的一对到最后一对，则遍历完一次，最后的元素就是最大的元素
     * 重复以上步骤直至排序完成
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        long start = System.currentTimeMillis();
        //i控制需要比较几趟
        for (int i = 0; i < arr.length - 1; i++) {
            //j控制每趟进行比较的次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.println("比较第几趟："+i);
        }
        long end = System.currentTimeMillis();
        System.out.println("冒泡耗时：" + (end - start));
        return arr;
    }

    /**
     * 冒泡排序优化：其实在进行比较时当两个元素没有发生交换即说明排序结束
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSortBetter(int[] arr) {
        long start = System.currentTimeMillis();
        //i控制需要比较几趟
        for (int i = 0; i < arr.length - 1; i++) {
            //用于标记某一趟是否有元素交换位置，默认没有
            boolean flag = false;
            //j控制每趟进行比较的次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            //System.out.println("比较第几趟："+i);
            //如果这一趟没有交换，则直接退出即可，说明已经排好序了
            if (!flag)
                break;
        }
        long end = System.currentTimeMillis();
        System.out.println("冒泡耗时：" + (end - start));
        return arr;
    }
}
