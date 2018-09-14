package linkedList;

/**
 * 单链表
 * @author LaZY（李志一）
 * @data 2018/9/14 - 16:56
 */
public class Singly_LinkedList {
    NodeBySingly_LinkedList headPoint = null;// 头指针，指向链表第一个元素

    /***
     * 单链表整表创建之头插法
     *
     * @param length
     *            创建后的单链表长度
     */
    public void initByInsertFromHead(int length) {
        NodeBySingly_LinkedList<Integer> headP = headPoint;// 声明头指针
        for (int i = 1; i <= length; i++) {
            NodeBySingly_LinkedList<Integer> node = new NodeBySingly_LinkedList<>();// 声明新节点
            node.data = i;// 为新节点赋值
            node.next = headPoint;// 新节点插入到链表第一个位置。
            headPoint = node;// 头指针重新指向第一个节点
        }
    }

    /***
     * 单链表的整表创建之尾插法
     *
     * @param length
     *            创建后的单链表的长度
     */
    public void initByInsertFromTill(int length) {
        NodeBySingly_LinkedList<Integer> tillP = new NodeBySingly_LinkedList<>();// 声明尾指针
        for (int i = 1; i <= length; i++) {
            NodeBySingly_LinkedList<Integer> node = new NodeBySingly_LinkedList<>();// 声明新节点
            node.data = i;// 为新节点赋值
            tillP.next = node;// 新节点插入到链表末尾
            tillP = node;// 尾指针移动到最后
            if (i == 1) {
                headPoint = node;// 头指针指向链表第一个节点
            }
        }
    }

    /***
     * 单链表的整表删除
     */
    public void deleteSingly_linkedList() {
        NodeBySingly_LinkedList p = headPoint;// 定义指针p指向头结点指向的节点
        NodeBySingly_LinkedList q = new NodeBySingly_LinkedList();// 定义指针q指向p的后继
        while (p.next != null) {
            q = p.next;// q指向p的后继
            p = p.next;// 指针p后移
            q = null;// 删除节点
        }
        System.out.println("单链表清空完毕");
    }

    /***
     * 清空单链表
     */
    public void clearSingly_LinkedList() {
        headPoint = null;
        System.out.println("单链表清空完毕");
    }

    /***
     * 打印单链表（的所有节点）
     */
    public void printSingly_LinkedList() {
        NodeBySingly_LinkedList node = headPoint;// 声明指针指向头指针指向的节点
        if (headPoint == null) {
            System.out.println("你访问的是一个空表");
        } else {
            System.out.print("[");
            while (true) {
                System.out.print(node);// 打印结果
                if (node.next != null) {// 当指针后面没有节点，打印完成
                    node = node.next;// 指针后移
                } else {
                    System.out.println("]");
                    break;
                }
                System.out.print(",");
            }
        }
    }

    /***
     * 在链表中查找指定位置的元素
     *
     * @param i
     *            查找的位置
     * @return 所查找的节点。当为null时，表示未查到。
     */
    public NodeBySingly_LinkedList locateNode(int i) {
        NodeBySingly_LinkedList p = null;
        if (checkLocation(i)) {// 检查查找的位置是否小于第一个位置
            p = headPoint;// 定义指针p指向链表第一个节点
            for (int j = 0; j < i - 1; j++) {// 指针从头移动到查找的位置
                if (p.next != null) {// 指针未指到末尾前保持移动
                    p = p.next;// 指针后移
                } else {// 指针移动到了末尾
                    System.out.println("位置不在范围内。");
                    p = null;// 指针移出链表
                    break;// 停止查找
                }
            }
        }
        return p;
    }

    /***
     * 对查找位置进行检查
     *
     * @param i
     *            查找的位置
     * @return t：合法 f：不和法（小于1）
     */
    public boolean checkLocation(int location) {
        if (location < 1) {
            System.out.println("查找位置不在范围内");
            return false;
        } else {
            return true;
        }
    }

    /***
     * 在指定位置插入指定节点
     *
     * @param location
     *            插入的位置
     * @param node
     *            插入的节点
     */
    public void insertNode(int location, NodeBySingly_LinkedList node) {
        if (checkLocation(location)) {// 检查位置是否小于第一个节点
            if (location == 1) {// 插入位置是链表头部
                node.next = headPoint;
                headPoint = node;
            } else {// 插入位置不是链表头部
                NodeBySingly_LinkedList n = locateNode(location - 1);// 检查插入位置的前一个位置是否在范围内
                if (n.next != null) {// 插入位置在范围内
                    node.next = n.next;// 连接右侧
                    n.next = node;// 连接左侧
                } else {
                    System.out.println("不在范围内");
                }
            }
        }
    }

    /***
     * 在指定位置的后面插入节点
     *
     * @param location
     *            指定位置（插入位置的前一个位置）
     * @param node
     *            待插入的节点
     * @return t:成功 f:失败
     */
    public boolean insert(int location, NodeBySingly_LinkedList node) {
        if (checkLocation(location)) {// 检查位置是否小于第一个节点
            NodeBySingly_LinkedList n = locateNode(location);// 查找并检查节点插入的位置（插入位置的前一个位置）
            if (n != null) {
                node.next = n.next;// 连接右链
                n.next = node;// 连接左链
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /***
     * 删除指定位置后面的节点。缺陷：无法删掉链表中的第一个节点。
     *
     * @param location
     *            指定位置（删除位置的前一个位置）
     * @return t:成功 f:失败
     */
    public boolean delete(int location) {
        if (checkLocation(location)) {// 检查位置是否小于第一个节点
            NodeBySingly_LinkedList n = locateNode(location);
            if (n != null && n.next != null) {// 删除位置的前一个位置在链表内，且不是末尾
                n.next = n.next.next;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
