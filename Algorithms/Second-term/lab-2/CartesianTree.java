import java.io.*;
import java.util.*;

public class CartesianTree {
    private static class Triple {
        int first;
        int second;
        int third;
        public Triple(int f, int s, int t) {
            first = f;
            second = s;
            third = t;
        }
        public String toString() {
            return "[ind: " + first + ", x: " + second + ", y: " + third + "]";
        }
    }
    private static class OutPair {
        int ind;
        String str;
        public OutPair(int ind, String str) {
            this.ind = ind;
            this.str = str;
        }

        public String toString() {
            return str;
        }
    }

    private static Triple[] tree;
    private static final ArrayList<OutPair> outPairs = new ArrayList<>();

    private static Triple calc(Triple f, Triple s) {
        if (f.third < s.third) {
            return f;
        } else {
            return s;
        }
    }

    private static void buildSegTree(ArrayList<Triple> list, int v, int vl, int vr) {
        if (vl + 1 == vr) {
            tree[v] = new Triple(vl, list.get(vl).first, list.get(vl).third);
            return;
        }
        int vm = vl + (vr - vl) / 2;
        buildSegTree(list, 2 * v + 1, vl, vm);
        buildSegTree(list, 2 * v + 2, vm, vr);
        tree[v] = calc(tree[2 * v + 1], tree[2 * v + 2]);
    }

    private static Triple getMin(int v, int vl, int vr, int ql, int qr) {
        if (ql >= vr || qr <= vl) {
            return new Triple(-1, -1, 1_000_001);
        }
        if (ql <= vl && vr <= qr) {
            return tree[v];
        }
        int vm = vl + (vr - vl) / 2;
        return calc(getMin(2 * v + 1, vl, vm, ql, qr), getMin(2 * v + 2, vm, vr, ql, qr));
    }

    private static int checkAbilityBST(List<Triple> list, int parent, int n, int l, int r) {
        if (l >= r) return 0;
        Triple mx = getMin(0, 0, n, l, r);
        int left = checkAbilityBST(list, mx.second, n, l, mx.first);
        int right = checkAbilityBST(list, mx.second, n, mx.first + 1, r);
        outPairs.add(new OutPair(mx.second, parent + " " + left + " " + right + '\n'));
        return mx.second;
    }

    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
            ArrayList<Triple> pWInd = new ArrayList<>();
            tree = new Triple[4 * n];
            for (int i = 1; i <= n; i++) {
                int[] info = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                pWInd.add(new Triple(i, info[0], info[1]));
            }
            pWInd.sort(Comparator.comparingInt((Triple a) -> a.second));
            buildSegTree(pWInd, 0, 0, n);
            checkAbilityBST(pWInd, 0, n, 0, n);
            outPairs.sort(Comparator.comparingInt((OutPair a) -> a.ind));
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                bw.write("YES\n");
                for (OutPair p : outPairs) {
                    bw.write(p.str);
                }
            } catch (IOException ignored) {}
        } catch (IOException ignored) {}
    }
}
