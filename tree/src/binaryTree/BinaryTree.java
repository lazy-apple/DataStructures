package binaryTree;

/**
 * 二叉树
 *
 * 部分功能用二叉查找树实现 缺陷：1.当前只能插入值为Integer类型的数据。2.只能在叶子节点的位置插入节点。
 *
 * 出错过的方法：getEmpty（）。
 *
 * @author LaZY（李志一）
 * @data 2018/9/14 - 17:02
 */
public class BinaryTree {

    BinaryTree_node root;// 二叉树的根节点
    int size = 0;// 二叉树中结点个数

    /***
     * 清空二叉树
     */
    public void clear() {
        this.root = null;
        size = 0;
    }

    /**
     * 二叉树的初始化
     */
    public void init() {
        clear();
    }

    /**
     * 判断二叉树是否为空树
     *
     * @return t:空树 f：非空
     */
    public boolean isEmpty() {// 二叉树中结点为0，则为空树
        if (this.size != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断二叉树是否为空树
     *
     * @return t:空树 f：非空
     */
    public boolean empty() {
        if (this.root == null) {// 根节点为空则二叉树为空树
            return false;
        } else {
            return true;
        }
    }


    /***
     * 二叉树的插入操作。使用二叉查找树实现
     *
     * ！当要查找的树枝为空，说明当前节点为要插入节点的父亲
     *
     * @param root
     *            当前二叉树的根节点
     * @param node
     *            要插入的节点
     * @return 插入完成时是：插入的新节点。 方法完成时是：二叉树的根节点（已经插入完成的红黑树）
     */
    private BinaryTree_node insert(BinaryTree_node root, BinaryTree_node node) {
        if (root == null) {// 访问的位置没有节点，就在这个位置插入新节点（递归的出口）
            size++;// 二叉树结点树加一
            return node;
        }
        if (node.comparTo(root) > 0) {// 插入的节点大于当前节点则放问右子树
            if (root.rightChildren == null) {// 条件成立时说明当前节点为要插入的节点的父亲。
                node.parent = root;
            }
            root.rightChildren = insert(root.rightChildren, node);// 将新节点插入到其右子树中
        } else if (node.comparTo(root) < 0) {// 插入的节点小于当前节点则放问左子树
            if (root.leftChildren == null) {
                node.parent = root;
            }
            root.leftChildren = insert(root.leftChildren, node);// 将新节点插入到其左子树中
        } else {// 插入的节点等于当前节点则插入的节点已存在
            System.out.println("结点已存在");// （递归的出口）
        }
        return root;
    }

    /***
     * 将指定结点插入到二叉树
     *
     * @param node
     *            要插入的节点
     */
    public void insert(BinaryTree_node node) {
        this.root = insert(this.root, node);
    }

    /***
     * 二叉树的删除操作。用二叉查找树实现
     *
     * @param root
     *            当前（正在访问的）二叉树的根节点
     * @param node
     *            要删除的节点
     * @return root 当前（正在访问的）二叉树的根节点
     */
    private BinaryTree_node delete(BinaryTree_node root, BinaryTree_node node) {
        if (root == null) {// 要访问的（子）树为空树,说明删除的节点不在访问的二叉树中
            System.out.println("删除的节点不在范围内");
            return root;
        }
        if (node.comparTo(root) > 0) {// 删除的节点大于当前（子）树的根节点
            root.rightChildren = delete(root.rightChildren, node);// 要删除的节点在当前（子）树的右子树中
        } else if (node.comparTo(root) < 0) {// 删除的节点小于当前（子）树的根节点
            root.leftChildren = delete(root.leftChildren, node);// 要删除的节点在当前（子）树的左子树中
        } else {// 删除的节点等于当前（子）树的根节点
            if (root.rightChildren != null && root.leftChildren != null) {// 要删除的节点左右儿子都存在
                root.data = findMin(root.rightChildren).data;// 把以当前节点的右儿子为根节点的子树中的最小节点的值，赋值给当前节点的数据域
                root.rightChildren = delete(root.rightChildren, root);// 在右儿子的子树中删掉刚刚查到的最小节点
            } else {// 要删除的节点只有一个或者没有儿子
                size--;// 二叉树中结点个数减一
                // 让当前位置指向它存在（或者空值）的儿子（把它存在（或者空值）的儿子赋值给当前位置）
                root = (root.leftChildren != null) ? root.leftChildren : root.rightChildren;
            }
        }
        return root;
    }

    /***
     * 在二叉树中删除指定节点
     *
     * @param node
     *            要删除的节点
     */
    public void delete(BinaryTree_node node) {
        this.root = delete(this.root, node);
    }

    /***
     * 查找二叉树中最小的节点
     *
     * @param root
     *            要查找的（子）树的根节点
     * @return root：要返回的节点
     */
    public BinaryTree_node findMin(BinaryTree_node root) {
        if (root == null) {// 当前节点为空（要遍历的子树为空树）//防止空指针
            return null;
        }

        if (root.leftChildren == null) {// 递归的出口

        } else {// 当前节点有左儿子，那么最小的结点就在当前节点的左子树中
            root = findMin(root.leftChildren);
        }
        return root;
    }

    /***
     * 以指定结点为根节点开始遍历其二叉树
     *
     * @param node
     *            遍历的起始点
     */
    public void printBefore(BinaryTree_node node) {
        if (node == null) {// 递归的出口

        } else {
            System.out.print(node);// 打印当前节点
            printBefore(node.leftChildren);// 访问左子树
            printBefore(node.rightChildren);// 访问右子树
        }
    }

    /***
     * 二叉树的先序遍历
     */
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
     * 获得二叉树中结点的个数
     *
     * @return 结点的个数
     */
    public int getSize() {
        return this.size;
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
    private BinaryTree_node getEmpty(BinaryTree_node root, BinaryTree_node node) {
        if (root == null) {// 访问的位置没有节点，则什么也没查到。（递归的出口）
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
     * 获得二叉树中指定的节点
     *
     * @param node
     *            指定要获得的节点
     */
    public BinaryTree_node getEmpty(BinaryTree_node node) {
        return getEmpty(this.root, node);
        // System.out.println(n);
    }

    /**
     * 获得指定节点的父节点
     *
     * @param node
     *            指定节点
     * @return 指定节点的父节点
     */
    public BinaryTree_node getParent(BinaryTree_node node) {
        return node.parent;
    }

    /**
     * 获得指定节点的祖父节点
     *
     * @param node
     *            指定节点
     * @return 指定节点的祖父节点
     */
    public BinaryTree_node getGrand(BinaryTree_node node) {
        if (node.parent != null) {
            return node.parent.parent;
        } else {
            return null;
        }
    }

    /***
     * 获得指定节点的兄弟
     *
     * @param node
     *            指定节点
     * @return 指定节点的兄弟
     */
    public BinaryTree_node getBrother(BinaryTree_node node) {
        if (node.parent != null && node.parent.leftChildren != null) {
            return node.parent.leftChildren.comparTo(node) == 0 ? node.parent.rightChildren : node.parent.leftChildren;
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
    public BinaryTree_node getUncle(BinaryTree_node node) {
        return this.getBrother(node.parent);
    }

    /***
     * 判断当前节点是否是其父节点的左孩子 解释：当前节点的父节点的左孩子是当前节点，则为左孩子。
     *
     * @param node
     *            要判断的当前节点
     * @return t:是左孩子 f：不是
     */
    public boolean isLeft(BinaryTree_node node) {
        if (node.parent != null) {
            return node.parent.leftChildren.comparTo(node) == 0;
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
    public boolean isRight(BinaryTree_node node) {
        return !(isLeft(node));
    }
}
