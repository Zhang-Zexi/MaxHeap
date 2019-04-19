/**
 * @ClassName Queue
 * @Description
 * @Author zhangzx
 * @Date 2019/3/28 14:00
 * Version 1.0
 **/
public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enQueue(E e);
    E deQueue();
    E getFront();
}
