package arrayList;

/**
 * 存放在线性表中元素的类
 * @author LaZY（李志一）
 * @data 2018/9/14 - 16:44
 */
public class Date<Type> {
    Type value;

    public Type getValue() {
        return value;
    }

    public void setValue(Type value) {
        this.value = value;
    }

    public Date(Type value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return "Date [value=" + value + "]";
    }
}
