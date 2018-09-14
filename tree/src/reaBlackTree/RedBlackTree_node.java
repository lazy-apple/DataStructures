package reaBlackTree;

/**
 * @author LaZY（李志一）
 * @data 2018/9/14 - 17:15
 */
public class RedBlackTree_node {
    public static final int BLACK = 1;
    public static final int RED = 0;
    public static RedBlackTree_node nullNode = new RedBlackTree_node(true);


    Integer data;// 数据域
    RedBlackTree_node leftChildren = nullNode;// 指向红黑树树左儿子的指针域
    RedBlackTree_node rightChildren = nullNode;// 指向红黑树右儿子的指针域
    RedBlackTree_node parent;// 指向红黑树双亲的指针域
    int color;// 颜色域

    public RedBlackTree_node(Integer data) {
        super();
        this.data = data;
    }

    public RedBlackTree_node() {
        super();
    }



    public RedBlackTree_node(boolean color) {
        this.color = 1;
    }

    @Override
    public String toString() {

        return "#RBnode [data=" + data + ", color=" + color         + "]";
        //+ ", parent=" + parent
    }



    public int comparTo(RedBlackTree_node node) {
        if (this.data > node.data) {
            return 1;
        } else if (this.data == node.data) {
            return 0;
        } else {
            return -1;
        }
    }
    public boolean compar(RedBlackTree_node node) {
        if (this==node) {
            return true;
        }else {
            return false;
        }
    }

    /***
     * 判断当前节点是否是null节点（即无任何属性），根据数据域是否为空进行判断
     *
     * @return t:是null节点 f:不是null节点
     */
    public boolean nullNode() {
        if (this.data == null) {// 数据域为空则为null节点
            return true;
        } else {
            return false;
        }
    }

    /***
     * 让当前节点变为nullnode
     *
     * @param node
     *            要变为nullnode的节点
     */
    public void beNull(RedBlackTree_node node) {
        node = new RedBlackTree_node();
    }

    public boolean isRed() {
        if (this.color == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBlack() {
        if (this.color == 1) {
            return true;
        } else {
            return false;
        }
    }

//	@Override
//	public String toString() {
//		return "RedBlackTree_node [data=" + data + ", leftChildren=" + leftChildren + ", rightChildren=" + rightChildren
//				+ "]";
//	}
}
