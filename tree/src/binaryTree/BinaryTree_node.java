package binaryTree;

/**
 * 二叉树结点
 * @author LaZY（李志一）
 * @data 2018/9/14 - 17:01
 */
public class BinaryTree_node {
    Integer data;// 数据域
    //Integer number;//节点的插入顺序
    BinaryTree_node leftChildren;// 指向二叉树左儿子的指针域
    BinaryTree_node rightChildren;// 指向二叉树右儿子的指针域
    BinaryTree_node parent;//指向二叉树双亲的指针域

    public BinaryTree_node() {
        super();
    }

    public BinaryTree_node(Integer data, BinaryTree_node leftChildren, BinaryTree_node rightChildren) {
        super();
        this.data = data;
        this.leftChildren = leftChildren;
        this.rightChildren = rightChildren;
    }

    public BinaryTree_node(Integer data) {
        super();
        this.data = data;
    }



//	@Override
//	public String toString() {
//		return "node [data=" + data + "] ";
//	}



    /***
     * 比较节点的大小
     *
     * @param node
     *            被比较的节点
     * @return 1：大； 0：相等 ； -1：小
     */
    public int comparTo(BinaryTree_node node) {
        if (this.data > node.data) {
            return 1;
        } else if (this.data == node.data) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "node [data=" + data + ", parent=" + parent + "] ";
    }
}
