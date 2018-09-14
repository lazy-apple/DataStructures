package reaBlackTree;

/**
 * @author LaZY（李志一）
 * @data 2018/9/14 - 17:18
 */
public class RedBlackTree_test {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.init();
//		if (tree.root.isBlack()) {
//			System.out.println("black");
//		}else {
//			System.out.println("red");
//		}
        tree.printBefore();
        tree.insert(new RedBlackTree_node(10));
        tree.insert(new RedBlackTree_node(85));
        tree.insert(new RedBlackTree_node(15));
        tree.insert(new RedBlackTree_node(70));
        tree.insert(new RedBlackTree_node(20));
        tree.insert(new RedBlackTree_node(60));
        tree.insert(new RedBlackTree_node(30));
        tree.insert(new RedBlackTree_node(50));
        tree.insert(new RedBlackTree_node(65));
        tree.insert(new RedBlackTree_node(80));
        tree.insert(new RedBlackTree_node(90));
        tree.insert(new RedBlackTree_node(40));
        tree.insert(new RedBlackTree_node(5));
        tree.insert(new RedBlackTree_node(55));

        tree.printBefore();
//		System.out.println("=========获得指定节点==========");
//		System.out.println(tree.getEmpty(new RedBlackTree_node(5)));
//		System.out.println("=========获得兄弟节点==========");
//		System.out.println(tree.getBrother(tree.getEmpty(new RedBlackTree_node(5))));
//		System.out.println("=========获得叔叔节点==========");
//		System.out.println(tree.getUncle(tree.getEmpty(new RedBlackTree_node(5))));
//		System.out.println("=========获得祖父节点==========");
//		System.out.println(tree.getGrand(tree.getEmpty(new RedBlackTree_node(3))));
//		System.out.println("=========判断节点颜色==========");
//		if (tree.getEmpty(new RedBlackTree_node(3)).isRed()) {
//			System.out.println("red");
//		}else {
//			System.out.println("black");
//		}
    }
}
