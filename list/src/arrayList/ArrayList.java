package arrayList;

import java.util.Arrays;

/**
 * 线性表
 * @author LaZY（李志一）
 * @data 2018/9/14 - 16:42
 */
    public class ArrayList {

        private final int MAXSIZE = 20;// 线性表的最大容量
        private Date<?>[] date = new Date[MAXSIZE];// 线性表的容器（数组）
        private int lenth = 0;// 线性表的长度（元素个数）

        /***
         * 向线性表插入元素
         *
         * @param list
         *            要操作的线性表
         * @param i
         *            插入的位置
         * @param element
         *            插入的元素
         * @return t 成功；f:失败
         */
        public boolean listInsert(arrayList.ArrayList list, int i, Date<?> element) {
            if (!(listInseCheck(list, i))) {// 检查
                return false;
            } else {
                insertList(list, i, element);
                System.out.println("插入成功");
                System.out.println(list);
                return true;
            }
        }

        /***
         * 判断线性表是否已满
         *
         * @param list
         *            要检查的线性表
         * @return t:未满；f:已满
         */
        private boolean listfull(arrayList.ArrayList list) {
            if (list.lenth < list.MAXSIZE + 1) {
                return true;
            } else {
                System.out.println("线性表容量已满，或超限");
                return false;
            }
        }

        /***
         * 判断元素插入位置是否合理（超过线性表范围）。 lenth+1为线性表末尾
         *
         * @param list
         *            要检查的线性表
         * @param i
         *            要插入的位置
         * @return t:合理；f:不合理
         */
        private boolean eleLocation(arrayList.ArrayList list, int i) {
            if (i > list.lenth + 1) {
                System.out.println("插入位置不在线性表范围内");
                return false;
            } else {
                return true;
            }
        }

        /***
         * 线性表插入操作前的检查： 1.线性表是否已满或大于线性表的容量。 2.插入的位置是否在线性表的长度内
         *
         * @param list
         *            要检查的线性表
         * @param i
         *            要插入的位置
         * @return t:合理；f:不合理
         */
        private boolean listInseCheck(arrayList.ArrayList list, int i) {
            if (!(listfull(list))) {
                return false;
            } else if (!(eleLocation(list, i))) {
                return false;
            } else {
                return true;
            }
        }

        /***
         * 插入元素
         *
         * @param list
         *            要操作的线性表
         * @param i
         *            插入的位置
         * @param element
         *            插入的元素
         */
        private void insertList(arrayList.ArrayList list, int i, Date<?> element) {
            for (int j = list.lenth - 1; j >= i - 1; j--) {
                list.date[j + 1] = list.date[j];
            }
            list.date[i - 1] = element;
            list.lenth++;
        }

        @Override
        public String toString() {
            return "ArrayList [date=" + Arrays.toString(date) + "]";
        }

        /***
         * 查找线性表内指定位置的元素
         *
         * @param list
         *            要操作的线性表
         * @param i
         *            查找的位置
         * @return 查找的元素
         */
        public Date<?> listLocate(arrayList.ArrayList list, int i) {
            if (checkLocate(list, i)) {
                return list.date[i - 1];
            } else {
                return null;
            }
        }

        /***
         * 检查插入位置
         *
         * @param list
         *            要操作的线性表
         * @param i
         *            查找的位置
         * @return t:位置合法 f:不合法
         */
        private boolean checkLocate(arrayList.ArrayList list, int i) {
            if (i > list.lenth) {
                System.out.println("位置不在线性表范围内");
                return false;
            } else {
                return true;
            }
        }

        /***
         * 检查删除位置：不能大于线性表范围，也不能小于1
         * @param list
         *            要操作的线性表
         * @param i
         *            查找的位置
         * @return t:位置合法 f:不合法
         */
        private boolean checkDelete(arrayList.ArrayList list, int i) {
            if (i > list.lenth) {
                System.out.println("位置不在线性表范围内");
                return false;
            } else if (i < 1) {
                System.out.println("删除的位置不能小于1");
                return false;
            } else {
                return true;
            }
        }

        /***
         * 删除线性表指定位置的元素
         *
         * @param list
         *            要操作的线性表
         * @param i
         *            查找的位置
         * @return t 成功；f:失败
         */
        public boolean listDelete(arrayList.ArrayList list, int i) {
            if (checkDelete(list, i)) {
                deleteList(list, i);
                System.out.println("删除成功");
                System.out.println(list);
                return true;
            } else {
                return false;
            }
        }

        /***
         * 删除操作
         *
         * @param list
         *            要操作的线性表
         * @param i
         *            删除的位置
         */
        private void deleteList(arrayList.ArrayList list, int i) {
            for (int j = i - 1; j <= list.lenth - 1; j++) {
                list.date[j] = list.date[j + 1];
            }
            list.lenth--;
        }

        public static void main(String[] args) {
            arrayList.ArrayList arrayList = new arrayList.ArrayList();
            Date<Integer> date = new Date<Integer>(1);
            arrayList.listInsert(arrayList, 1, date);
            arrayList.listInsert(arrayList, 2, date);
            arrayList.listInsert(arrayList, 3, date);
            Date<Integer> date2 = new Date<Integer>(2);
            arrayList.listInsert(arrayList, 2, date2);// 插入
            System.out.println("线性表第2个位置的元素为："+arrayList.listLocate(arrayList, 2));// 查找
            arrayList.listDelete(arrayList, 2);// 删除
        }

}
