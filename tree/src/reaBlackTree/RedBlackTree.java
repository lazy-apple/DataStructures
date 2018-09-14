package reaBlackTree;

/**
 * 红黑树（非JDK的TreeMap源码）
 *
 * 出现过的问题：
 * 1.插入调整：
 * 		a.当指向根节点，无需调整。
 * 		b.nullNode节点的颜色为黑色（不定义的话，默认为红色）
 * 2.插入:
 * 		应该在递归之前完成插入。插入功能结束之后再调整。先调整再插入再递归会使结构损坏。
 * 3.旋转：
 * 		a.旋转操作以后要让当前第一层的节点连接其上面的节点。
 * 			但是，当第一层的上面已经没有节点了（即为根节点），就不可以在连接。正确做法是，让根节点指向该节点。
 * 		b.旋转操作后要保证每个节点各个属性的完整（特别是parent属性）
 * 4.判断两个节点是否相等的方法应给在不同功能有不同实现：
 * 		a.当遍历的时候，比较的两个节点均不为nullNode节点，可以通过比较数据域的大小来比较两个节点的大小。
 * 		b.当其中有nullNode节点的时候，要比较两个节点是否相等用“==”
 * 5.判断一个节点是其父节点的左孩子还是右孩子的方法也应该使用“==”，而不是比较数据域。
 *
 * @author LaZY（李志一）
 * @data 2018/9/14 - 17:16
 */
public class RedBlackTree {
    RedBlackTree_node root = new RedBlackTree_node();// 红黑树的根节点
    RedBlackTree_node point = new RedBlackTree_node();// 备用指针（用于红黑树的调整）
    int size = 0;// 红黑树中结点个数

    /***
     * 清空红黑树
     */
    public void clear() {
        // this.root = new RedBlackTree_node();
        this.root.beNull(this.root);// 让红黑树的根节点指向nullnode节点
        size = 0;
    }

    /***
     * 红黑树初始化
     */
    public void init() {
        clear();
        this.root.color = 1;// 让根节点颜色为黑
    }

    /***
     * 判空
     *
     * @return f:空 t:非空
     */
    public boolean empty() {
        if (this.root.nullNode()) {// 根节点为空则红黑树为空树
            return false;
        } else {
            return true;
        }
    }

    /***
     * 红黑树的插入操作
     *
     * @param root
     *            当前子树的根节点
     * @param node
     *            要插入的节点
     * @return 插入完成时是：插入的新节点。 方法完成时是：红黑树的根节点（已经插入完成的红黑树）
     */
    private RedBlackTree_node insert(RedBlackTree_node root, RedBlackTree_node node) {
        if (root.nullNode()) {// 访问的位置为nullnode节点，就让新节点覆盖nullnode节点（返回当前节点）
            size++;// 红黑树结点数加一
            point = node;// 备用节点指向新插入的节点
            return node;
        }
        int compar = node.comparTo(root);// 插入的节点和当前节点比较
        if (compar > 0) {// 插入的节点大于当前节点则放问右子树
            if (root.rightChildren.data == null) {// 条件成立时说明当前节点为要插入的节点的父亲。
                node.parent = root;
            }
            root.rightChildren = insert(root.rightChildren, node);// 将新节点插入到其右子树中
        } else if (compar < 0) {// 插入的节点小于当前节点则放问左子树
            if (root.leftChildren.data == null) {
                node.parent = root;
            }
            root.leftChildren = insert(root.leftChildren, node);// 将新节点插入到其左子树中
        } else {// 插入的节点等于当前节点则插入的节点已存在
            System.out.println("已存在");// （递归的出口）
        }
        return root;
    }

    /***
     * 在红黑树中插入指定节点
     *
     * @param node
     *            要插入的节点
     */
    public void insert(RedBlackTree_node node) {
        if (getEmpty(node) == null) {// 节点存在时，不允许插入红黑树中
            if (isEmpty()) {
                this.root = node;// 红黑树为空树时节点直接插入
                this.size++;
            } else {
                this.root = insert(this.root, node);
                if (this.point!=null) {
                    reorient(this.point);
                }
            }
            point = null;
            this.root.color = 1;// 红黑树的条件，根节点必须为黑色节点
        }
    }

    public void printBefore(RedBlackTree_node node) {
        if (node.nullNode()) {// 递归的出口

        } else {
            System.out.print(node);// 打印当前节点
            printBefore(node.leftChildren);// 访问左子树
            printBefore(node.rightChildren);// 访问右子树
        }
    }

    public void printBefore() {
        if (isEmpty()) {// 当访问空树的时候给出提示
            System.out.println("！！！你访问的是一颗空树！！！");
        } else {
            System.out.print("先序遍历结果为：【");// 格式
            printBefore(this.root);// 遍历二叉树
            System.out.println("】");// 格式
        }
    }

    /***
     * 红黑树判空
     *
     * @return t:空树 f:非空
     */
    public boolean isEmpty() {// 红黑树中真实节点为0（即不包括nullnode在内的节点），即为空树
        if (this.size != 0) {
            return false;
        } else {
            return true;
        }
    }

    /***
     * 根据当前的根节点在子树中查找指定节点
     *
     * @param root
     *            当前的根节点
     * @param node
     *            要查找的指定节点
     * @return node 查找到的节点
     */
    private RedBlackTree_node getEmpty(RedBlackTree_node root, RedBlackTree_node node) {
        if (root.nullNode()) {// 访问的位置没有节点，则什么也没查到。（递归的出口）
            return null;
        }
        int compar = node.comparTo(root);
        if (compar > 0) {// 要查找的节点大于当前节点则查找右子树
            return getEmpty(root.rightChildren, node);// 将新节点插入到其右子树中
        } else if (compar < 0) {// 要查找的节点小于当前节点则查找左子树
            return getEmpty(root.leftChildren, node);// 将新节点插入到其左子树中
            // ！！！！！！！！错误写法！！！！！！！！！！！！！！！：
            // return root.leftChildren = getEmpty(root.leftChildren, node);
            // ！！！！！！！！出错原因：会把子树的根节点改变，变为找到的节点
        } else {// 查找的节点等于当前节点。
            return root;
        }
    }

    /***
     * 获得红黑树中指定的节点
     *
     * @param node
     *            指定要获得的节点
     */
    public RedBlackTree_node getEmpty(RedBlackTree_node node) {
        return getEmpty(this.root, node);
    }

    /**
     * 获得指定节点的父节点
     *
     * @param node
     *            指定节点
     * @return 指定节点的父节点
     */
    public RedBlackTree_node getParent(RedBlackTree_node node) {
        return node.parent;
    }

    /***
     * 获得指定节点的兄弟
     *
     * @param node
     *            指定节点
     * @return 指定节点的兄弟
     */
    public RedBlackTree_node getBrother(RedBlackTree_node node) {
        if (!(node.parent == null) && (!((node.parent.leftChildren) == null))) {
            return node.parent.leftChildren.compar(node) ? node.parent.rightChildren : node.parent.leftChildren;
        } else {
            return null;
        }
    }

    /**
     * 获得指定节点的叔叔（指定节点父节点的另一个儿子）
     *
     * @param node
     *            指定的节点
     * @return 指定节点的叔叔
     */
    public RedBlackTree_node getUncle(RedBlackTree_node node) {
        return this.getBrother(node.parent);
    }

    /**
     * 获得指定节点的祖父节点
     *
     * @param node
     *            指定节点
     * @return 指定节点的祖父节点
     */
    public RedBlackTree_node getGrand(RedBlackTree_node node) {
        if (node.parent != null) {
            return node.parent.parent;
        } else {
            return null;
        }
    }

    /***
     * 判断当前节点是否是其父节点的左孩子 解释：当前节点的父节点的左孩子是当前节点，则为左孩子。
     *
     * @param node
     *            要判断的当前节点
     * @return t:是左孩子 f：不是
     */
    public boolean isLeft(RedBlackTree_node node) {
        if (node.parent != null) {
            return node.parent.leftChildren.compar(node);
        } else {// 当前节点无父节点，则不能判断
            System.out.println("无父节点");
            return false;
        }
    }

    /***
     * 判断当前节点是否是其父节点的右孩子 解释：当前节点不是左孩子即为右孩子
     *
     * @param node
     *            要判断的当前节点
     * @return t:是右孩子 f：不是
     */
    public boolean isRight(RedBlackTree_node node) {
        if (node.parent != null) {
            return node.parent.rightChildren.compar(node);
        } else {// 当前节点无父节点，则不能判断
            System.out.println("无父节点");
            return false;
        }
    }

    public boolean right(RedBlackTree_node node) {
        if (node.parent.rightChildren == node) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * 红黑树的左旋转
     *
     * @param root
     *            旋转轴（当前节点）
     * @return 轴节点（及其子树）
     */
    public RedBlackTree_node rotateLeft(RedBlackTree_node root) {
        RedBlackTree_node parent = getParent(root);
        RedBlackTree_node lc = root.leftChildren;
        boolean b = false;
        if (parent!=this.root) {
            b = isRight(parent);
        }
        if (parent.comparTo(root) != 0) {
            RedBlackTree_node grand = getGrand(root);
            root.parent.rightChildren = root.leftChildren;// 轴节点（当前节点）的父节点的右儿子连接轴节点的左儿子
            root.leftChildren = parent;// 轴节点的左儿子连接轴节点的父亲
            root.parent = grand;
            root.rightChildren.parent = root;
            parent.parent = root;
            lc.parent = parent;
            if (parent==this.root) {
                this.root = root;
            }else {
                if (b) {
                    grand.rightChildren = root;
                } else {
                    grand.leftChildren = root;
                }
            }
        } else {
            root.parent.rightChildren = root.leftChildren;// 轴节点（当前节点）的父节点的右儿子连接轴节点的左儿子
            root.leftChildren = root.parent;// 轴节点的左儿子连接轴节点的父亲
        }
        return root;
    }

    /***
     * 红黑树的右旋转
     *
     * @param root
     *            旋转轴（当前节点）
     * @return 轴节点（及其子树）
     */
    public RedBlackTree_node rotateRight(RedBlackTree_node root) {
        RedBlackTree_node parent = getParent(root);
        RedBlackTree_node rg = root.rightChildren;
        boolean b = false;
        if (parent!=this.root) {
            b = isRight(parent);
        }
        if (parent.comparTo(root) != 0) {
            RedBlackTree_node grand = getGrand(root);
            root.parent.leftChildren = root.rightChildren;// 轴节点的父亲的左儿子连接轴节点的右儿子
            root.rightChildren = root.parent;// 轴节点的右儿子连接轴节点的父亲
            root.parent = grand;
            parent.parent = root;
            rg.parent = parent;
            if (parent==this.root) {
                this.root = root;
            }else {
                if (b) {
                    grand.rightChildren = root;
                } else {
                    grand.leftChildren = root;
                }
            }
        } else {
            root.parent.leftChildren = root.rightChildren;// 轴节点的父亲的左儿子连接轴节点的右儿子
            root.rightChildren = root.parent;// 轴节点的右儿子连接轴节点的父亲
        }
        return root;
    }

    public RedBlackTree_node leftCase1(RedBlackTree_node node) {
        node.parent.color = 1;// p染黑
        getUncle(node).color = 1;// y染黑
        getGrand(node).color = 0;// g染红
        return getGrand(node);// 指向g
    }

    public RedBlackTree_node rightCase1(RedBlackTree_node node) {
        node.parent.color = 1;// p染黑
        getUncle(node).color = 1;// y染黑
        getGrand(node).color = 0;// g染红
        return getGrand(node);// 指向g
    }

    public void rightCase2(RedBlackTree_node node) {
        RedBlackTree_node p = node.parent;
        rotateRight(node);// x为轴，右旋
        rightCase3(p);// 指向p
    }

    public void leftCase2(RedBlackTree_node node) {
        RedBlackTree_node p = node.parent;
        rotateLeft(node);// x为轴，左旋
        leftCase3(p);// 指向p
    }

    public void rightCase3(RedBlackTree_node node) {
        RedBlackTree_node p = node.parent;
        node.parent.color = 1;// p变黑
        getGrand(node).color = 0;// g变红
        rotateLeft(p);// p为轴左旋
    }

    public void leftCase3(RedBlackTree_node node) {
        RedBlackTree_node p = node.parent;
        node.parent.color = 1;// p变黑
        getGrand(node).color = 0;// g变红
        rotateRight(p);// p为轴，右旋
    }

    public RedBlackTree_node reorient(RedBlackTree_node node) {
        if (node.comparTo(this.root) == 0) {// 要调整的节点为根节点则不调整

        } else if (node.parent.isBlack()) {// 插入节点的父节点为黑的，直接插入,不调整

        } else {
            RedBlackTree_node p = getParent(node);
            RedBlackTree_node g = getGrand(node);
            if (isLeft(p)) {// p为g的左孩子
                if (getUncle(node).isRed()) {// y为红
                    RedBlackTree_node case1 = leftCase1(node);
                    reorient(case1);
                } else if (getUncle(node).isBlack() && isRight(node)) {// y为黑
                    // x为右孩子
                    leftCase2(node);
                } else if (getUncle(node).isBlack() && isLeft(node)) {// y为黑,x为左孩子
                    leftCase3(node);
                }
            } else if (isRight(p)) {// p为g的右孩子
                if (getUncle(node).isRed()) {// y为红
                    RedBlackTree_node case1 = rightCase1(node);
                    reorient(case1);
                } else if (getUncle(node).isBlack() && isLeft(node)) {// y为黑,x为左孩子
                    rightCase2(node);
                } else if (getUncle(node).isBlack() && isRight(node)) {// y为黑,x为右孩子
                    rightCase3(node);
                }
            }

        }
        return null;
    }
}
