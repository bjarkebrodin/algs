public class ByteArrayList implements ByteStack {

    public ByteArrayList() {
        this(DEFAULT_CAP);
    }

    public ByteArrayList(int initialCapacity) {
        sz = 0;
        A = new byte[initialCapacity];
    }

    public void push(byte t) {
        ensureCap();
        A[sz++] = t;
    }

    public byte pop() {
        freeUnused();
        return A[--sz];
    }

    static private final int DEFAULT_CAP = 8;

    private int sz;

    private byte[] A;

    private void ensureCap() {
        if (sz == A.length)
            resize(sz << 1);
    }

    private void freeUnused() {
        if (sz <= A.length >> 2)
            resize(A.length >> 1);
    }

    private void resize(int target) {
        byte[] B = new byte[target];
        System.arraycopy(A, 0, B, 0, sz);
        A = B;
    }

}