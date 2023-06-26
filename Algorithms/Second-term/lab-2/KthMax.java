import java.io.*;
import java.util.Arrays;

public class KthMax {

    private static class Treap {
        private static class Node {
            int value;
            int priority;
            int count;
            int rightCount;
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
            r.rightCount = rght;
        }

        private Pair split(Node r, int x0) {
            if (r == null) return new Pair(null, null);
            if (r.value <= x0) {
                Pair p = split(r.right, x0);
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
            Node node = new Node(x, getPriority());
            Pair p1 = split(root, x - 1);
            Pair p2 = split(p1.second, x);
            root = merge(p1.first, merge(node, p2.second));
            recalcCount(root);
        }

        public void remove(int x) {
            Pair p1 = split(root, x - 1);
            Pair p2 = split(p1.second, x);
            root = merge(p1.first, p2.second);
            recalcCount(root);
        }

        public int findKthMax(int k, Node cur) {
            if (cur == null) return Integer.MIN_VALUE;
            if (k == cur.rightCount + 1) {
                return cur.value;
            } else if (k < cur.rightCount + 1) {
                return findKthMax(k, cur.right);
            } else {
                return findKthMax(k - cur.rightCount - 1, cur.left);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
            Treap treap = new Treap();
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                while (n > 0) {
                    int[] line = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    switch (line[0]) {
                        case 1 -> {
                            treap.insert(line[1]);
                        }
                        case 0 -> {
                            bw.write(treap.findKthMax(line[1], treap.root) + "\n");
                        }
                        case -1 -> {
                            treap.remove(line[1]);
                        }
                    }
                    n--;
                }
            } catch (IOException ignored) {
            }
        } catch (IOException ignored) {
        }
    }
}
