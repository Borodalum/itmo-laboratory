import java.io.*;
import java.util.Arrays;

public class BSTArray {
    private static class ImplicitTreap {
        private static class Node {
            int value;
            int priority;
            int count;
            int leftCount;
            Node left;
            Node right;

            public Node() {
            }

            public Node(int x, int y) {
                value = x;
                priority = y;
                count = 1;
            }
        }

        private Node root = null;

        private static class Pair {
            Node first;
            Node second;

            public Pair(Node f, Node s) {
                first = f;
                second = s;
            }
        }

        private int getPriority() {
            return 1000 + (int) (Math.random() * 10000);
        }

        private void recalcCount(Node r) {
            if (r == null) return;
            int lft, rght;
            if (r.left == null) {
                lft = 0;
            } else {
                lft = r.left.count;
            }
            if (r.right == null) {
                rght = 0;
            } else {
                rght = r.right.count;
            }
            r.count = lft + rght + 1;
            r.leftCount = lft;
        }

        private Pair split(Node r, int x0) {
            if (r == null) return new Pair(null, null);
            if (r.leftCount <= x0) {
                Pair p = split(r.right, x0 - r.leftCount - 1);
                r.right = p.first;
                recalcCount(r);
                return new Pair(r, p.second);
            } else {
                Pair p = split(r.left, x0);
                r.left = p.second;
                recalcCount(r);
                return new Pair(p.first, r);
            }
        }

        private Node merge(Node r1, Node r2) {
            if (r1 == null) return r2;
            if (r2 == null) return r1;
            if (r1.priority > r2.priority) {
                r1.right = merge(r1.right, r2);
                recalcCount(r1);
                return r1;
            } else {
                r2.left = merge(r1, r2.left);
                recalcCount(r2);
                return r2;
            }
        }

        public void insert(int x) {
            Node newNode = new Node(x, getPriority());
            Pair p1 = split(root, x - 1);
            Pair p2 = split(p1.second, x);
            root = merge(p1.first, merge(newNode, p2.second));
        }

        public void move(int x1, int x2) {
            Pair p1 = split(root, x1 - 1);
            Pair p2 = split(p1.second, x2 - x1);
            root = merge(p2.first, merge(p1.first, p2.second));
        }

        public void inOrder(Node cur, StringBuilder sb) {
            if (cur == null) return;
            inOrder(cur.left, sb);
            sb.append(cur.value).append(" ");
            inOrder(cur.right, sb);
        }

    }

    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nm = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = nm[0];
            int m = nm[1];
            ImplicitTreap treap = new ImplicitTreap();
            for (int i = 1; i <= n; i++) {
                treap.insert(i);
            }
            while (m > 0) {
                int[] segment = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                treap.move(segment[0] - 1, segment[1] - 1);
                m--;
            }
            StringBuilder sb = new StringBuilder();
            treap.inOrder(treap.root, sb);
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                bw.write(sb.toString());
            } catch (IOException ignored) {}
        } catch (IOException ignored) {}
    }
}
