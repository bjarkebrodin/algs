import static java.lang.System.*;
import java.util.Arrays;

public class StackBench {
    static int runsBench = 100;
    static int sampleSize;

    public static void main(String[] args) {
        sampleSize = Integer.parseInt(args[0]);

        out.println();
        runBench(() -> testGenericIntStack(false), Array.class, "push\tInteger");
        runBench(() -> testGenericIntStack(true), Array.class, "push + pop\tInteger");

        out.println();
        runBench(() -> testIntStack(false), IntArrayList.class, "push");
        runBench(() -> testIntStack(true), IntArrayList.class, "push + pop");


        out.println();
        runBench(() -> testByteStack(false), ByteArrayList.class, "push");
        runBench(() -> testByteStack(true), ByteArrayList.class, "push + pop");

        out.println();
        runBench(() -> testGenericByteStack(false), Array.class, "push\tByte");
        runBench(() -> testGenericByteStack(true), Array.class, "push + pop\tByte");

        out.println();
        runBench(() -> testBoolStack(false), BoolArray.class, "push");
        runBench(() -> testBoolStack(true), BoolArray.class, "push + pop");


        out.println();
    }

    static void runBench(Runnable run, Class impl, String notes) {
        out.print(bench(run) + " ms\t");
        out.println(impl.getName() + "\t[" + notes + "]");
    }

    static long bench(Runnable run) {
        long[] times = new long[runsBench];
        for (int i = 0; i < runsBench; i++) {
            times[i] = time(run);
            gc();
        }
        Arrays.sort(times);
        return times[runsBench / 2];
    }

    static long time(Runnable run) {
        long start = currentTimeMillis();
        run.run();
        long end = currentTimeMillis();
        return end - start;
    }

    static void testIntStack(boolean pop) {
        IntStack stack = new IntArrayList();
        for (int i = 0; i < sampleSize; i++)
            stack.push(1);
        if (!pop)
            return;
        for (int i = 0; i < sampleSize; i++)
            stack.pop();
    }

    static void testGenericIntStack(boolean pop) {
            Stack<Integer> stack = new Array<Integer>();
            for (int i = 0; i < sampleSize; i++)
                stack.push(1);
            if (!pop) return;
            for (int i = 0; i < sampleSize; i++)
                stack.pop();
    }

    static void testByteStack(boolean pop) {
        ByteStack stack = new ByteArrayList();
        for (int i = 0; i < sampleSize; i++)
            stack.push((byte) 1);
        if (!pop)
            return;
        for (int i = 0; i < sampleSize; i++)
            stack.pop();
    }

    static void testGenericByteStack(boolean pop) {
            Stack<Byte> stack = new Array<Byte>();
            for (int i = 0; i < sampleSize; i++)
                stack.push((byte)1);
            if (!pop) return;
            for (int i = 0; i < sampleSize; i++)
                stack.pop();
    }

    static void testBoolStack(boolean pop) {
        BoolStack stack = new BoolArray();
        for (int i = 0; i < sampleSize; i++)
            stack.push(true);
        if (!pop)
            return;
        for (int i = 0; i < sampleSize; i++)
            stack.pop();
    }


}