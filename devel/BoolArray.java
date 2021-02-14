
public class BoolArray implements BoolStack {
     
    public BoolArray() {
        this(DEFAULT_CAP);
    }

    public BoolArray(int initialCapacity) {
        sz = 0;
        A = new boolean[initialCapacity];
    }

    public void push(boolean t) {
        ensureCap();
        A[sz++] = t;
    }

    public boolean pop() {
        freeUnused();
        return A[--sz];
    }

    static private final int DEFAULT_CAP = 8;

    private int sz;

    private boolean[] A;

    private void ensureCap() {
        if (sz == A.length)
            resize(sz << 1);
    }

    private void freeUnused() {
        if (sz <= A.length >> 2)
            resize(A.length >> 1);
    }

    private void resize(int target) {
        boolean[] B = new boolean[target];
        System.arraycopy(A, 0, B, 0, sz);
        A = B;
    }

}
