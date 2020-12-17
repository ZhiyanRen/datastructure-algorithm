package com.test.tree;

/**
 * @program: datastructure-algorithm
 * @Date: 2020/12/16 22:13
 * @Author: Mrs.Ren
 * @Description: 红黑树相关操作API
 */
public class RedBlackTree<Key extends Comparable<Key>> {

    //根节点
    private Node<Key> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    class Node<Key extends Comparable<Key>> {
        //父节点
        private Node<Key> parent;
        //左孩子
        private Node<Key> left;
        //右孩子
        private Node<Key> right;
        //节点key
        private Key key;
        //颜色
        private boolean color;

        private Node(Node<Key> parent, Node<Key> left, Node<Key> right, Key key, boolean color) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.key = key;
            this.color = color;
        }

        //方便调试看节点的值和颜色
        public String toString() {
            return "" + key + (this.color == RED ? "R" : "B");
        }
    }

    /**
     * 在整棵红黑树中插入node节点，插入以后保证该树还是红黑树
     *
     * @param key 插入节点数据
     */

    public void insert(Key key) {
        Node<Key> node = new Node<Key>(null, null, null, key, BLACK);

        if (node != null)
            insert(node);
    }

    private void insert(Node<Key> node) {
        //1.先将节点添加到该红黑树中（实质为二叉查找树）
        Node<Key> x = this.root;
        Node<Key> y = null;
        int cmp;

        //比较当前节点和x节点的值，找到当前节点的插入位置
        while (null != x) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                //继续往左找
                x = x.left;
            } else {
                //继续往右找
                x = x.right;
            }
        }

        //设置node的父节点
        node.parent = y;
        if (null != y) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        } else {
            this.root = node;
        }

        //2.设置该节点颜色为红色
        node.color = RED;

        //3.重新修正该二叉查找树为红黑树
        insertFixUp(node);
    }

    /**
     * 红黑树插入修正
     * 在向红黑树中插入节点之后如果失去平衡，调该方法进行修复使其平衡，将其重新塑造为一棵红黑树
     *
     * @param node 插入节点
     */
    private void insertFixUp(Node<Key> node) {
        //声明当前节点的父亲节点、祖父节点
        Node<Key> parent, gparent;

        while (null != ((parent = parentOf(node))) && isRed(parent)) {
            //获取祖父节点
            gparent = parentOf(parent);

            //1、若“父节点”是“祖父节点的左孩子”：
            //左旋时：以当前节点和父亲节点进行左旋
            //右旋时：以父亲节点和祖父节点进行右旋
            if (parent == gparent.left) {
                //①情况一变色：如果叔叔节点是红色，则需要变色：父亲、叔叔节点变黑，祖父节点变红
                Node<Key> uncle = gparent.right;
                if ((null != uncle) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    //此时当前节点的父节点和叔叔节点为黑色，肯定正常，而祖父节点变为红色，
                    // 所以需要考虑祖父节点的父亲、叔叔节点是否正常，所以将当前节点设为祖父节点
                    node = gparent;
                    continue;
                }

                //②情况二左旋：如果叔叔是黑色，且当前节点是父亲节点右孩子，那么以当前节点和父亲节点进行左旋
                //左旋完：当前节点和父亲节点交换位置，此时当前节点变成父亲节点，父亲节点变为当前节点的左孩子
                if (parent.right == node) {
                    leftRotate(parent);
                    //左旋完后：交换parent和node的表示，维护当前节点、父亲、祖父关系
                    Node<Key> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //③情况三右旋：如果叔叔是黑色，且当前节点是父亲节点左孩子，那么以父亲节点和祖父节点进行右旋
                //并且将父亲节点设为黑色，祖父节点设为红色，因为此时祖父节点变为父亲节点的右孩子
                //此时进行完右旋后，父亲节点和当前节点关系不变
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {
                //2、若“父节点”是“祖父节点的右孩子”：
                // 左旋时：以父亲节点和祖父节点进行左旋；
                // 右旋时：以当前节点和父亲节点进行右旋；

                //①情况一变色：如果叔叔节点是红色，则需要变色：父亲、叔叔节点变黑，祖父节点变红
                Node<Key> uncle = gparent.left;
                if ((null != uncle) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    //此时当前节点的父节点和叔叔节点为黑色，肯定正常，而祖父节点变为红色，
                    // 所以需要考虑祖父节点的父亲、叔叔节点是否正常，所以将当前节点设为祖父节点
                    node = gparent;
                    continue;
                }

                //②情况二右旋：如果叔叔是黑色，且当前节点是父亲节点左孩子，那么以当前节点和父亲节点进行右旋
                //右旋完：当前节点和父亲节点交换位置，此时当前节点变成父亲节点，父亲节点变为当前节点的右孩子
                if (parent.left == node) {
                    Node<Key> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //③情况三左旋：如果叔叔是黑色，且当前节点是父亲节点右孩子，那么以父亲节点和祖父节点进行左旋
                //并且将父亲节点设为黑色，祖父节点设为红色，因为此时祖父节点变为父亲节点的左孩子
                //此时左旋完当前节点和父亲节点关系保持不变
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }

        //3、将根节点设为黑色
        setBlack(this.root);
    }

    /**
     * 左旋：将当前节点和其右孩子节点进行左旋
     *
     * @param x 当前节点
     */
    private void leftRotate(Node<Key> x) {
        //设置x的右孩子为y
        Node<Key> y = x.right;

        // 将“y的左孩子”设为“x的右孩子”；
        // 如果y的左孩子非空，将 “x” 设为 “y的左孩子的父亲”
        x.right = y.left;
        if (null != y.left) {
            y.left.parent = x;
        }

        // 将“x的父亲”设为“y的父亲”
        y.parent = x.parent;
        if (null == x.parent) {
            //如果 “x的父亲” 是空节点，则将y设为根节点
            this.root = y;
        } else if (x == x.parent.left) {
            //如果x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
            x.parent.left = y;
        } else {
            //如果x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
            x.parent.right = y;
        }

        //将“y的左孩子”设为“x”
        y.left = x;

        // 将“x的父节点”设为“y”
        x.parent = y;
    }

    /**
     * 右旋：将当前节点和其左孩子节点进行右旋
     *
     * @param y 当前节点
     */
    private void rightRotate(Node<Key> y) {
        //设置x是当前节点的左孩子
        Node<Key> x = y.left;

        //将“x的右孩子”设为“y的左孩子”
        // 如果"x的右孩子"不为空的话，将 “y” 设为 “x的右孩子的父亲”
        y.left = x.right;
        if (null != x.right) {
            x.right.parent = y;
        }

        //将“y的父亲”设为“x的父亲”
        x.parent = y.parent;
        if (null == y.parent) {
            // 如果“y的父亲”是空节点，则将x设为根节点
            this.root = x;
        } else if (y == y.parent.right) {
            //如果y是它父节点的右孩子，则将x设为“y的父节点的右孩子”
            y.parent.right = x;
        } else {
            //如果y是它父节点的左孩子,则将x设为“y的父节点的左孩子”
            y.parent.left = x;
        }

        //将“y”设为“x的右孩子”
        x.right = y;
        //将“y的父节点”设为“x”
        y.parent = x;
    }


    /**
     * 获取当前节点的父亲节点
     * 非空树中根节点是唯一一个父节点为null的节点
     *
     * @param node
     * @return
     */
    private Node<Key> parentOf(Node<Key> node) {
        return node != null ? node.parent : null;
    }

    /**
     * 判断当前节点的颜色是否为红色
     *
     * @param node 当前节点
     * @return
     */
    private boolean isRed(Node<Key> node) {
        return ((node != null) && (node.color == RED)) ? true : false;
    }

    /**
     * 设置当前节点颜色为黑色
     *
     * @param node 当前节点
     */
    private void setBlack(Node<Key> node) {
        if (node != null)
            node.color = BLACK;
    }

    /**
     * 设置当前节点颜色为红色
     *
     * @param node 当前节点
     */
    private void setRed(Node<Key> node) {
        if (node != null)
            node.color = RED;
    }

    /**
     * 从根节点前序遍历"红黑树"
     */
    public void preOrder() {
        preOrder(this.root);
    }

    private void preOrder(Node<Key> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }


}
