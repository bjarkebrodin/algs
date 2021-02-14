
public class IntArrayList implements IntStack {

    public IntArrayList() {
        this(DEFAULT_CAP);
    }

    public IntArrayList(int initialCapacity) {
        sz = 0;
        A = new int[initialCapacity];
    }

    public void push(int t) {
        ensureCap();
        A[sz++] = t;
    }

    public int pop() {
        freeUnused();
        return A[--sz];
    }

    static private final int DEFAULT_CAP = 8;

    private int sz;

    private int[] A;

    private void ensureCap() {
        if (sz == A.length)
            resize(sz << 1);
    }

    private void freeUnused() {
        if (sz <= A.length >> 2)
            resize(A.length >> 1);
    }

    private void resize(int target) {
        int[] B = new int[target];
        System.arraycopy(A, 0, B, 0, sz);
        A = B;
    }

}