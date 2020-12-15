package com.test.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program：datastructure-algorithm
 * @Author：Mrs.Ren
 * @Date：2020-12-14 22:31
 * @Description： 二叉查找树相关操作API接口
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    //根节点
    private Node root;
    //树节点数
    private int size;

    /**
     * 树节点
     */
    public class Node {
        //键
        private Key key;
        //值
        private Value value;
        //左子树
        private Node left;
        //右子树
        private Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 向整个树中插入一个键值对
     *
     * @param key   新添加元素key
     * @param value 新添加元素value
     * @return 返回添加后的新树
     */
    public Node put(Key key, Value value) {
        return put(root, key, value);
    }

    /**
     * 向子树x中插入节点
     * 思路：
     * 1.如果当前树中没有任何一个结点，则直接把新结点当做根结点使用
     * 2.如果当前树不为空，则从根结点开始：
     * 2.1如果新结点的key小于当前结点的key，则继续找当前结点的左子结点；
     * 2.2如果新结点的key大于当前结点的key，则继续找当前结点的右子结点；
     * 2.3如果新结点的key等于当前结点的key，则树中已经存在这样的结点，替换该结点的value值即可。
     *
     * @param x     x子树
     * @param key   新添加元素key
     * @param value 新添加元素value
     * @return 返回添加后的新树
     */
    public Node put(Node x, Key key, Value value) {
        //如果x子树为空,则直接添加为根节点
        if (null == x) {
            size++;
            return new Node(key, value, null, null);
        }

        //如果x子树不为空
        //比较x结点的键和key的大小：
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            //如果新结点的key小于当前结点的key，则继续找当前结点的左子结点
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            //如果新结点的key大于当前结点的key，则继续找当前结点的右子结点；
            x.right = put(x.right, key, value);
        } else {
            //如果新结点的key等于当前结点的key，则树中已经存在这样的结点，替换该结点的value值即可。
            x.value = value;
        }
        return x;
    }

    /**
     * 获取树中元素的个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 根据key，从整个树中找出对应的值
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 从指定子树X中找出key对应的值
     * 思路：
     * 从根节点开始：
     * 1.如果要查询的key小于当前结点的key，则继续找当前结点的左子结点；
     * 2.如果要查询的key大于当前结点的key，则继续找当前结点的右子结点；
     * 3.如果要查询的key等于当前结点的key，则树中返回当前结点的value。
     *
     * @param x
     * @param key
     * @return
     */
    private Value get(Node x, Key key) {
        //如果子树x不存在
        if (null == x) {
            return null;
        }

        //x树不为null
        //比较key和x结点的键的大小
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            //如果key小于x结点的键，则继续找x结点的左子树
            return get(x.left, key);
        } else if (cmp > 0) {
            //如果key大于x结点的键，则继续找x结点的右子树
            return get(x.right, key);
        } else {
            //如果key等于x结点的键，就找到了键为key的结点，只需要返回x结点的值即可
            return x.value;
        }
    }

    /**
     * 根据key，删除树中对应的键值对
     *
     * @param key
     * @return 删除的节点
     */
    public Node delete(Key key) {
        return delete(root, key);
    }

    /**
     * 删除指定树x上的键为key的键值对，并返回删除后的新树
     * 思路：
     * 1.找到被删除结点；
     * 2.找到被删除结点右子树中的最小结点minNode
     * 3.删除右子树中的最小结点
     * 4.让被删除结点的左子树称为最小结点minNode的左子树，让被删除结点的右子树称为最小结点minNode的右子树
     * 5.让被删除结点的父节点指向最小结点minNode
     *
     * @param x
     * @param key
     * @return
     */
    public Node delete(Node x, Key key) {
        if (null == x) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            //如果key小于x结点的键，则继续找x结点的左子树
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            //如果key大于x结点的键，则继续找x结点的右子树
            x.right = delete(x.right, key);
        } else {
            //如果key等于x结点的键，完成真正的删除结点动作，要删除的结点就是x；

            //让元素个数-1
            size--;


            //找到被删除结点右子树中的最小结点minNode
            if (x.right == null) {
                return x.left;
            }

            if (x.left == null) {
                return x.right;
            }

            Node minNode = x.right;
            while (null != minNode.left) {
                minNode = minNode.left;
            }

            //删除右子树中最小的结点
            Node node = x.right;
            while (null != node.left) {
                if (null == node.left.left) {
                    //此时node.left就是最小节点,将该节点删除
                    node.left = null;
                } else {
                    //继续遍历
                    node = node.left;
                }
            }

            //让x结点的左子树成为minNode的左子树
            minNode.left = x.left;
            //让x结点的右子树成为minNode的右子树
            minNode.right = x.right;
            //让x结点的父结点指向minNode
            minNode = x;
        }
        return x;
    }

    /**
     * 查找整个树中最小的键
     *
     * @return
     */
    public Key min() {
        return min(root).key;
    }

    /**
     * 在指定树x中找出最小键所在的结点
     *
     * @param x
     * @return
     */
    public Node min(Node x) {
        //判断x还有没有左子结点，如果有，则继续向左找，如果没有，则x就是最小键所在的结点
        if (null == x) {
            return null;
        }

        if (null == x.left) {
            return x;
        } else {
            return min(x.left);
        }
    }

    /**
     * 查找整个树中最大的键
     *
     * @return
     */
    public Key max() {
        return max(root).key;
    }

    /**
     * 在指定树x中找出最大键所在的结点
     *
     * @param x
     * @return
     */
    public Node max(Node x) {
        //判断x还有没有右子结点，如果有，则继续向右找，如果没有，则x就是最小键所在的结点
        if (null == x) {
            return null;
        }

        if (null == x.right) {
            return x;
        } else {
            return max(x.right);
        }
    }

    /**
     * 获取整个树中所有的键
     *
     * @return
     */
    public Queue<Key> preErgodic() {
        Queue<Key> keys = new LinkedList<>();
        preErgodic(root, keys);
        return keys;
    }

    /**
     * 先序遍历（根左右）：获取指定树x的所有键，并放到keys队列中
     *
     * @param x
     * @param keys
     */
    public void preErgodic(Node x, Queue<Key> keys) {
        if (null == x) {
            return;
        }

        keys.add(x.key);

        //递归遍历x左子树
        if (null != x.left) {
            preErgodic(x.left, keys);
        }

        //递归遍历x右子树
        if (null != x.right) {
            preErgodic(x.right, keys);
        }
    }

    /**
     * 获取整个树中所有的键
     *
     * @return
     */
    public Queue<Key> midErgodic() {
        Queue<Key> keys = new LinkedList<>();
        midErgodic(root, keys);
        return keys;
    }

    /**
     * 中序遍历（左根右）：获取指定树x的所有键，并放到keys队列中
     *
     * @param x
     * @param keys
     * @return
     */
    public void midErgodic(Node x, Queue<Key> keys) {
        if (null == x) {
            return;
        }

        //递归遍历x左子树
        if (null != x.left) {
            midErgodic(x.left, keys);
        }

        //放当前节点根节点
        keys.add(x.key);

        //递归遍历x右子树
        if (null != x.right) {
            midErgodic(x.right, keys);
        }
    }

    /**
     * 获取整个树中所有的键
     *
     * @return
     */
    public Queue<Key> afterErgodic() {
        Queue<Key> keys = new LinkedList<>();
        afterErgodic(root, keys);
        return keys;
    }

    /**
     * 后序遍历（左右根）：获取指定树x的所有键，并放到keys队列中
     *
     * @param x
     * @param keys
     * @return
     */
    public void afterErgodic(Node x, Queue<Key> keys) {
        if (null == x) {
            return;
        }

        //递归遍历x左子树
        if (null != x.left) {
            midErgodic(x.left, keys);
        }

        //递归遍历x右子树
        if (null != x.right) {
            midErgodic(x.right, keys);
        }

        //放当前节点根节点
        keys.add(x.key);
    }

    /**
     * 使用层序遍历：从上到下，从左到右
     * 思路：
     * 1.创建队列，存储每一层的结点；
     * 2.使用循环从队列中弹出一个结点：
     * 2.1获取当前结点的key；
     * 2.2如果当前结点的左子结点不为空，则把左子结点放入到队列中
     * 2.3如果当前结点的右子结点不为空，则把右子结点放入到队列中
     *
     * @return
     */
    public Queue<Key> layerErgodic() {
        //定义两个队列，分别存储树中的键和树中的结点
        Queue<Key> keys = new LinkedList<>();
        Queue<Node> nodes = new LinkedList<>();

        //默认，往队列中放入根结点
        nodes.add(root);
        while (!nodes.isEmpty()) {
            //从队列中弹出一个结点，把key放入到keys中
            Node node = nodes.poll();
            keys.add(node.key);

            //判断当前结点还有没有左子结点，如果有，则放入到nodes中
            if (null != node.left) {
                nodes.add(node.left);
            }

            //判断当前结点还有没有右子结点，如果有，则放入到nodes中
            if (null != node.right) {
                nodes.add(node.right);
            }
        }
        return keys;
    }

    /**
     * 获取整个树的最大深度
     *
     * @return
     */
    public int maxDepth() {
        return maxDepth(root);
    }

    /**
     * 获取指定树x的最大深度
     *
     * @param x
     * @return
     */
    public int maxDepth(Node x) {
        if (x == null) {
            return 0;
        }
        //x的最大深度
        int max = 0;
        //左子树的最大深度
        int maxL = 0;
        //右子树的最大深度
        int maxR = 0;

        if (null != x.left) {
            maxL = maxDepth(x.left);
        }

        if (null != x.right) {
            maxR = maxDepth(x.right);
        }

        //比较左子树最大深度和右子树最大深度，取较大值+1即可
        max = maxL < maxR ? maxR + 1 : maxL + 1;
        return max;
    }
}
