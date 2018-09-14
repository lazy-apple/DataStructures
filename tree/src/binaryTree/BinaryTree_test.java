package binaryTree;

/**
 * @author LaZY（李志一）
 * @data 2018/9/14 - 17:04
 */
public class BinaryTree_test {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        System.out.println("--------------二叉树的初始化------------------\n");
        binaryTree.init();
        System.out.println("查看初始化后的二叉树为：");
        binaryTree.printBefore();
        System.out.println("--------------向二叉树中插入数据--------------\n");
        System.out.println("按顺序插入：6、2、8、1、5、3、4");
        binaryTree.insert(new BinaryTree_node(6));
        binaryTree.insert(new BinaryTree_node(2));
        binaryTree.insert(new BinaryTree_node(8));
        binaryTree.insert(new BinaryTree_node(1));
        binaryTree.insert(new BinaryTree_node(5));
        binaryTree.insert(new BinaryTree_node(3));
        binaryTree.insert(new BinaryTree_node(4));
        System.out.println("查看二叉树的结果：");
        binaryTree.printBefore();
        System.out.println("验证：二叉树中结点个数为"+binaryTree.getSize());
        System.out.println("--------------获取二叉树中指定节点的数据-------\n");
        System.out.println("获取值为3和1的二叉树的节点");
        System.out.println(binaryTree.getEmpty(new BinaryTree_node(3)));
        System.out.println(binaryTree.getEmpty(new BinaryTree_node(1)));
//		System.out.println("--------------向二叉树中删除数据--------------\n");
//		System.out.println("删除二叉树中值为 2 的节点");
//		binaryTree.delete(new BinaryTree_node(2));
//		System.out.println("删除后的二叉树为：");
//		binaryTree.printBefore();
//		System.out.println("验证：二叉树中结点个数为"+binaryTree.getSize());
        System.out.println("--------获得父节点----------------");
        System.out.println(binaryTree.getParent(binaryTree.getEmpty(new BinaryTree_node(3))));
        System.out.println("--------获得祖父节点------------");
        System.out.println(binaryTree.getGrand(binaryTree.getEmpty(new BinaryTree_node(4))));
        System.out.println("--------获得兄弟---------------");
        System.out.println(binaryTree.getBrother(binaryTree.getEmpty(new BinaryTree_node(5))));
        System.out.println("---------获得叔叔-------------");
        System.out.println(binaryTree.getUncle(binaryTree.getEmpty(new BinaryTree_node(3))));
        System.out.println("--------判断指定节点是否为左孩子-------");
        if (binaryTree.isLeft(binaryTree.getEmpty(new BinaryTree_node(3)))) {
            System.out.println("是左孩子");
        }else {
            System.out.println("不是左孩子");
        }
        System.out.println("--------判断指定节点是否为右孩子-------");
        if (binaryTree.isRight(binaryTree.getEmpty(new BinaryTree_node(5)))) {
            System.out.println("是右孩子");
        }else {
            System.out.println("不是右孩子");
        }
    }
}
