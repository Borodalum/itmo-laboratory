import java.util.NoSuchElementException;
import java.util.Scanner;

public class RSQ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        long[] tree = new long[4 * n];
        buildTree(arr, tree, 0, 0, n);

        String command = sc.next();
        try {
            while (sc.hasNext()) {
                switch (command) {
                    case "set" -> {
                        int i = sc.nextInt() - 1;
                        setValue(tree, 0, 0, n, i, sc.nextLong());
                    }
                    case "sum" -> {
                        int i = sc.nextInt() - 1;
                        int j = sc.nextInt();
                        System.out.println(getSum(tree, 0, 0, n, i, j));
                    }
                }
                command = sc.next();
            }
        } catch (NoSuchElementException e) {
            sc.close();
        }
    }

    private static void buildTree(long[] a, long[] tree, int v, int vl, int vr) {
        if (vl + 1 == vr) {
            tree[v] = a[vl];
            return;
        }
        int vm = vl + (vr - vl)/2;
        buildTree(a, tree, 2*v + 1, vl, vm);
        buildTree(a, tree, 2*v + 2, vm, vr);

        tree[v] = tree[2*v + 1] + tree[2*v + 2];
    }

    private static long getSum(long[] tree, int v, int vl, int vr, int ql, int qr) {
        if (vr <= ql || vl >= qr) {
            return 0;
        }
        if (ql <= vl && vr <= qr) {
            return tree[v];
        }

        int vm = vl + (vr - vl)/2;
        return getSum(tree, 2*v + 1, vl, vm, ql, qr) + getSum(tree, 2*v + 2, vm, vr, ql, qr);
    }

    private static void setValue(long[] tree, int v, int vl, int vr, int index, long value) {
        if (vr <= index || index < vl) {
            return;
        }
        if (vl == index && vl + 1 == vr) {
            tree[v] = value;
            return;
        }

        int vm = vl + (vr - vl)/2;
        setValue(tree, 2*v + 1, vl, vm, index, value);
        setValue(tree, 2*v + 2, vm, vr, index, value);

        tree[v] = tree[2*v + 1] + tree[2*v + 2];
    }

}
