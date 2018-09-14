package linkedList;

/**
 * 单链表的节点
 * @author LaZY（李志一）
 * @data 2018/9/14 - 16:53
 */
public class NodeBySingly_LinkedList<Type> {
    public Type data;//数据域
    public NodeBySingly_LinkedList<Type> next;//指针域


    @Override
    public String toString() {
        return "node [data=" + data + "]";
    }

    public NodeBySingly_LinkedList() {
        super();
    }

    public NodeBySingly_LinkedList(Type data) {
        super();
        this.data = data;
    }
}
