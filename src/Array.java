/**
 * @ClassName Array
 * @Description
 * @Author zhangzx
 * @Date 2019/3/22 11:08
 * Version 1.0
 **/
public class Array<E> {

    private E[] data;
    private int size;

    // 添加容量
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 默认容量
    public Array() {
        this(10);
    }

    // 获取长度
    public int getSize() {
        return size;
    }

    // 获取容量
    public int getCapacity() {
        return data.length;
    }

   // 判断长度是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //在最后添加元素
    public void addLast(E e) {

//        if(size == data.length) {
//            throw new IllegalArgumentException("AddLast failed. Array is full");
//        }
//        //data[size++] = e ;
//        data[size] = e;
//        size ++ ;
        add(size, e);
    }

//    在最前添加元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 添加元素
    public void add (int index, E e) {

        //添加的元素的索引不能小于0
        //添加元素的索引不能大于数据的索引+1（数组的长度），因为所有元素都必须相连
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("index Exception");
        }

        // 如果数据的个数和容量相等，则无法添加元素
        if(size == data.length) {
//            throw new IllegalArgumentException("AddLast failed. Array is full");
            resize(2 * data.length);
            //ps:在ArrayList中扩容的是1.5
        }

        //size-1是最后一个索引
        //如果添加的索引大于最后一个索引，那么就要出现移位现象。比如最后一个索引就要往后移动一位
        //大于添加索引的元素都要移位，所以i--
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        //把添加的值，放到要添加的索引位置
        data[index] = e;
        //添加之后长度+1
        size++;
    }

    E get(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal");
        }
        return data[index];
    }

    void set(int index, E e) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed. Index is illegal");
        }
        data[index] = e;
    }

    public boolean contains(int e) {
        for(int i = 0; i < size; i++) {
            if(data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for(int i = 0; i < size; i++) {
            if(data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) {

        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("index Exception");
        }
        E ret = data[index];
        for(int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size -- ;
        data[size] = null; //loitering objects != memory leak

        if(size == data.length / 4 && data.length / 2 != 0 ) {
            resize(data.length/2);
        }
        return ret;
    }

    public E removeFirst() {
      return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if(index != -1) {
            remove(index);
        }
    }

    public void swap(int i, int j) {

        if(i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Arrays: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0; i < size; i++) {
            res.append(data[i]);
            if(i != size-1) {
                res.append(",");
            }
        }
        res.append(']');
        return  res.toString();
    }

    //实现数组的扩容操作
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
