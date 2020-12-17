package com.test.tree;

/**
 * @program: datastructure-algorithm
 * @Date: 2020/12/17 7:26
 * @Author: Mrs.Ren
 * @Description: 红黑树操作测试
 */
public class RedBlackTreeTest {
    private static final int a[] = {19, 5, 30, 1, 12, 35, 7, 13, 6};


    public static void main(String[] args) {
        int i, ilen = a.length;
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        System.out.printf("原始数据: ");
        for (i = 0; i < ilen; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        for (i = 0; i < ilen; i++) {
            tree.insert(a[i]);
        }

        System.out.printf("前序遍历: ");
        tree.preOrder();
    }
}
