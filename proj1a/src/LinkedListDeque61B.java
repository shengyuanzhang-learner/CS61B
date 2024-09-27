import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private IntNode sentinel;
    private IntNode last;

    private int size;
    public class IntNode {
        public T first;
        public IntNode next;
        public IntNode before;

        public IntNode(T f, IntNode b, IntNode n) {
            first = f;
            next = n;
            before = b;
        }
    }
    public LinkedListDeque61B() {
        sentinel = new IntNode(null,null,null);
        last = sentinel;
    }

    @Override
    public void addFirst(T x) {
        IntNode n = new IntNode(x,sentinel,null);
        if(sentinel.next==null){
            sentinel.before = n;
            last = n;
            n.next = sentinel;
        }else {
            sentinel.next.before = n;
            n.next = sentinel.next;
        }
        sentinel.next = n;
        this.size++;
    }

    @Override
    public void addLast(T x) {
        IntNode n = new IntNode(x,last,sentinel);
        last.next = n;
        last = n;
        sentinel.before = last;
        this.size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        IntNode p = sentinel;
        while (p.next!=sentinel){
            p = p.next;
            returnList.add(p.first);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next==null && sentinel.before == null){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        IntNode n = sentinel.next;
        T remove_t;
        if(sentinel.next==null && sentinel.before == null){
            return null;
        }
        if(size==1){
            last = sentinel;
            sentinel.next = null;
            sentinel.before = null;
            return n.first;
        }
        else{
            remove_t = sentinel.next.first;
            sentinel.next = sentinel.next.next;
            this.size--;
            sentinel.next.before = sentinel;
            return remove_t;
        }
    }

    @Override
    public T removeLast() {
        IntNode n = last;
        T remove_t;
        if(sentinel.before==null){
            return null;
        }
        if(size==1){
            last = sentinel;
            sentinel.before = null;
            sentinel.next = null;
            return n.first;
        }
        else{
            remove_t = sentinel.before.first;
            last = n.before;
            last.next = sentinel;
            sentinel.before = last;
            this.size--;
            return remove_t;
        }
    }

    @Override
    public T get(int index) {
        IntNode n = sentinel;
        int i = 0;
        while (i != index){
            i ++;
            n = n.next;
        }
        return n.next.first;
    }
    public T getRecursive(int index , IntNode n){
        if (index == 0){
            return n.next.first;
        }
        else{
            return getRecursive(index - 1, n.next);
        }
    }
    @Override
    public T getRecursive(int index) {
        IntNode n = sentinel;
        return getRecursive(index , n);
    }
}
