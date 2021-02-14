
public class Array<T> implements Stack<T> {

    public Array() {
        this(DEFAULT_CAP);
    }

    public Array(int initialCapacity) {
        sz = 0;
        A = array(initialCapacity);
    }

    public void push(T t) {
        ensureCap();
        A[sz++] = t;
    }

    public T pop() {
        freeUnused();
        return A[--sz];
    }

    static private final int DEFAULT_CAP = 8;

    private int sz;

    private T[] A;

    private void ensureCap() {
        if (sz == A.length)
            resize(sz << 1);
    }

    private void freeUnused() {
        if (sz <= A.length >> 2)
            resize(A.length >> 1);
    }

    private void resize(int target) {
        T[] B = array(target);
        System.arraycopy(A, 0, B, 0, sz);
        A = B;
    }

    @SuppressWarnings("unchecked")
    private T[] array(int n) {
        T[] A = (T[]) new Object[n];    
        return A;
    }

}