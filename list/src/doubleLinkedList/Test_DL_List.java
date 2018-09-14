package doubleLinkedList;

/**
 * @author LaZY（李志一）
 * @data 2018/9/14 - 16:51
 */
public class Test_DL_List {
    public static void main(String[] args) {
        Double_LinkedList list = new Double_LinkedList();//声明双链表对象
        System.out.println("---------------双向链表的初始化-----------------");
        list.init();//初始化
        list.initAllListByTail();//整表创建
        list.print();//打印链表
        System.out.println("---------------获取双向链表元素-----------------");
        System.out.println("链表长度为"+list.getLenth());//获取链表长度
        System.out.println(list.getEmpty(11));//获取元素
        System.out.println("---------------在双向链表插入元素-----------------");
        list.insert(new NodeBYDouble_linkedList("hahaha"), 5);
        System.out.println("链表长度为"+list.getLenth());//获取链表长度
        list.print();//验证链表
        System.out.println("---------------在双向链表删除元素-----------------");
        list.delete(6);
        System.out.println("链表长度为"+list.getLenth());//获取链表长度
        list.print();//验证链表
    }
}
