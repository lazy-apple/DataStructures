package doubleLinkedList;

/**
 * 双向链表的节点
 * @author LaZY（李志一）
 * @data 2018/9/14 - 16:46
 */
public class NodeBYDouble_linkedList<Type> {
    public NodeBYDouble_linkedList next;//指向后继的指针域
    public NodeBYDouble_linkedList pre;//指向前驱的指针域
    public Type data;//数据域

    public NodeBYDouble_linkedList(Type data,NodeBYDouble_linkedList pre,NodeBYDouble_linkedList next) {
        super();
        this.next = next;
        this.pre = pre;
        this.data = data;
    }
    public NodeBYDouble_linkedList() {
        super();
    }

    public NodeBYDouble_linkedList(Type data) {
        super();
        this.data = data;
    }
    @Override
    public String toString() {
        return "Node [data=" + data + "]";
    }

}
