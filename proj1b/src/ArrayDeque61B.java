import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private int size;
    private int head;
    private int tail;
    private int capacity;
    public T[] array;
    public ArrayDeque61B() {
        array = (T[]) new Object[8];
        this.size = 0;
        this.head = array.length - 1;
        this.tail = 0;
        this.capacity = 8;
    }
    private void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        for(int i = head; i < array.length ; i++ ){
            newArray[capacity - array.length + i] = array[i];
        }
        for(int i = 0; i < tail ; i++){
            newArray[i] = array[i];
        }
        array = newArray;
        int head1 = capacity - this.head;
        int tail1 = this.tail;
        this.head = head1 - 1;
        this.tail = tail1;
        this.capacity = capacity;
    }

    @Override
    public void addFirst(T x) {
        if(size == capacity){
            resize(capacity * 2);
        }
        array[head] = x;
        head--;
        size++;
    }

    @Override
    public void addLast(T x) {
        if(size == capacity){
            resize(capacity * 2);
        }
        array[tail] = x;
        tail++;
        size++;

    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<T>();
        for(int i = head; i < array.length; i++){
            list.add(array[i]);
        }
        for(int i = tail; i >= 0; i--){
            list.add(array[i]);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T x = array[head+1];
        array[head+1] = null;
        head++;
        size--;
        return x;
    }

    @Override
    public T removeLast() {
        T x = array[tail-1];
        array[tail-1] = null;
        tail--;
        size--;
        return x;
    }

    @Override
    public T get(int index) {
        return array[(index+head+1)%array.length];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
