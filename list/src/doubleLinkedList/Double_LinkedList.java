package doubleLinkedList;


/**
 * 双向链表
 *
 *  出现过的错误1：插入方法不能在链表的尾部插入。（原因：getElement（）方法中检查元素的位置不能大于表的长度。解决方法：新定义尾插方法inserttill（））
 *
 * @author LaZY（李志一）
 * @data 2018/9/14 - 16:48
 */
public class Double_LinkedList {

    NodeBYDouble_linkedList begin;// 双向链表的头结点
    NodeBYDouble_linkedList end;// 双向链表的尾节点
    // NodeBYDouble_linkedList headPoint;// 双向链表的头指针
    int size = 0;// 双向链表的长度

    /***
     * 双向链表的初始化
     */
    public void init() {
        clear();
    }

    /***
     * 判断双向链表是否为空表
     *
     * @return t:为空 f:不为空
     */
    public boolean isEmpty() {
        if (size == 0) {// 链表长度为0，证明链表为空
            return true;
        } else {
            return false;
        }
    }

    /***
     * 清空双向链表（利用头尾节点）
     */
    public void clear() {
        begin = new NodeBYDouble_linkedList(null, null, null);
        end = new NodeBYDouble_linkedList(null, begin, null);// 链表尾节点连接头结点（尾节点前驱指向头结点）
        begin.next = end;// 链表头节点连接尾结点（头结点后继指向尾节点）
        size = 0;
    }

    /***
     * 双向链表整表创建（尾插法） 注意： 1.插入节点后，要连接前驱 2.插入最后一个节点后，最后一个节点的后继指向尾节点。否则会出现空指针问题。
     */
    public void initAllListByTail() {
        clear();
        NodeBYDouble_linkedList p = begin;// 定义一个指针指向链表头结点
        for (int i = 1; i <= 10; i++) {
            NodeBYDouble_linkedList<Integer> node = new NodeBYDouble_linkedList<>(i);// 定义要插入的节点，并赋值
            node.pre = p;// 连接新节点的前驱
            p.next = node;// 连接新节点的后继
            size++;
            p = p.next;// 指针后移
            if (i == 10) {// 插入最后一个节点后，最后一个节点的后继指向尾节点。
                p.next = end;// 最后一个节点的后继指向尾节点。
                break;// 结束
            }
        }
    }

    public NodeBYDouble_linkedList getEmpty(int location) {
        if (checkLocation(location, size)) {
            NodeBYDouble_linkedList p = begin;
            for (int i = 0; i < location; i++) {
                p = p.next;
            }
            return p;
        } else {
            return null;
        }
    }

    /***
     * 检查查找节点是否在范围内
     *
     * @param location
     *            查找的位置
     * @param max范围的右边界
     * @return t:在范围 f:不在范围
     */
    public boolean checkLocation(int location, int max) {
        if (location > 0 && location <= max) {
            return true;
        } else {
            System.out.println("查找位置不在范围内!");
            return false;
        }
    }

    /***
     * 向双向链表中插入元素
     *
     * @param node要插入的节点
     * @param location插入位置
     * @return t:成功 f:失败
     */
    public boolean insert(NodeBYDouble_linkedList node, int location) {
        if (checkLocation(location, size + 1)) {// 检查插入的位置是否在范围内
            NodeBYDouble_linkedList p = begin;// 定义指针p指向头结点
            if (location == size + 1) {// 从表尾插入节点
                addtill(node);
                return true;
            } else {
                p = getEmpty(location);// 指针p指向插入位置的节点
                if (p != null) {// 查找的节点存在
                    p.pre.next = node;// 连接节点前驱1(指定位置的前一个节点的后继指向新节点)
                    node.pre = p.pre;// 连接节点前驱2（新节点的前驱指向它的前一个节点）
                    node.next = p;// 连接节点后继1（新节点的后继指向插入位置的节点）
                    p.pre = node;// 连接节点后继1（插入位置节点的前驱指向新节点）
                    size++;// 链表长度加一
                    return true;
                } else {
                    return false;
                }
            }

        } else {
            return false;
        }
    }

    /***
     * 从链表的表尾插入节点
     *
     * @param node
     *            要插入的节点
     */
    public void addtill(NodeBYDouble_linkedList node) {
        NodeBYDouble_linkedList p = end;//定义指针p指向尾节点
        end.pre.next = node;//连接新节点的前链1：尾节点的前驱的后继指向新节点
        node.pre = end.pre;////连接新节点的前链2：新节点的前驱指向尾节点的前一个节点
        node.next = end;//连接新节点的后链1：新节点的后继指向尾节点。
        end.pre = node;//连接新节点的后链2：尾节点的前驱指向新节点。
    }

    /***
     * 删除指定位置的节点
     *
     * @param location
     *            删除的位置
     * @return t:成功 f:失败
     */
    public boolean delete(int location) {
        if (checkLocation(location, size)) {// 检查插入的位置是否在范围内
            NodeBYDouble_linkedList p = begin;// 定义指针p指向头结点
            p = getEmpty(location);// 指针p指向插入位置的节点。注意判空
            if (p != null) {
                p.next.pre = p.pre;// 断开前链
                p.pre.next = p.next;// 断开后链
                size--;// 链表长度减一
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int getLenth() {
        return size;
    }

    public void print() {
        NodeBYDouble_linkedList p = begin;// 定义一个指针指向链表头结点
        if (p.next == end) {
            System.out.println("你访问的是一个空表");
        }
        System.out.print("【");
        while (p.next != end) {// 当指针指向的下一个节点不为尾节点，打印并指针后移
            System.out.print(p.next);
            p = p.next;
        }
        System.out.println("】");
    }

}
