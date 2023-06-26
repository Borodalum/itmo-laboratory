import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KeysAddingBST {

    private static class ZeroTreap {
        private static Node root = null;
        private static int getPriority() {
            return 1000 + (int)(Math.random() * 10000);
        }
        private static class Node {
            long value;
            int y;
            Node left;
            Node right;
            public Node(long v, int y) {
                this.value = v;
                this.y = y;
            }

            public Node() {}
        }
        private static class Pair {
            Node first;
            Node second;
            public Pair(Node f, Node s) {
                first = f;
                second = s;
            }
        }
        private static Pair split(long x, Node node) {
            if (node == null) return new Pair(null, null);
            if (node.value <= x) {
                Pair k = split(x, node.right);
                node.right = k.first;
                return new Pair(node, k.second);
            } else {
                Pair k = split(x, node.left);
                node.left = k.second;
                return new Pair(k.first, node);
            }
        }
        private static Node merge(Node r1, Node r2) {
            if (r1 == null) return r2;
            if (r2 == null) return r1;
            if (r1.y > r2.y) {
                r1.right = merge(r1.right, r2);
                return r1;
            } else {
                r2.left = merge(r1, r2.left);
                return r2;
            }
        }
        private static void insert(long x) {
            if (exists(x)) return;
            Node newNode = new Node(x, getPriority());
            Pair p1 = split(x - 1, root);
            Pair p2 = split(x, p1.second);
            root = merge(p1.first, merge(newNode, p2.second));
        }
        private static void delete(long x) {
            if (!exists(x)) return;
            Pair p1 = split(x - 1, root);
            Pair p2 = split(x, p1.second);
            root = merge(p1.first, p2.second);
        }
        private static boolean exists(long x) {
            Pair p1 = split(x - 1, root);
            Pair p2 = split(x, p1.second);
            root = merge(p1.first, merge(p2.first, p2.second));
            return p2.first != null;
        }
        private static long next(Node r, long x) {
            if (r == null) return Long.MAX_VALUE;
            if (r.value > x) {
                return Math.min(next(r.left, x), r.value);
            } else {
                return next(r.right, x);
            }
        }
        public static void inOrder(Node cur, ArrayList<Long> sb) {
            if (cur == null) return;
            inOrder(cur.left, sb);
            sb.add(cur.value);
            inOrder(cur.right, sb);
        }
    }
    private static class ImplicitTreap {
        private int mxIndex = Integer.MIN_VALUE;
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

        private int sizeOf(Node root) {
            return root == null ? 0 : root.count;
        }

        public void delete(int x) {
            Pair p1 = split(root, x - 1);
            Pair p2 = split(p1.second, x - sizeOf(p1.first));
            root = merge(p1.first, p2.second);
        }

        public void insertKey(int x, int value, boolean isZero) {
            Node newNode = new Node(value, getPriority());
            Pair p1 = split(root, x - 1);
            Pair p2 = split(p1.second, x - sizeOf(p1.first));
            if (p2.first == null || p2.first.value == 0) {
                ZeroTreap.delete(x + 1);
                root = merge(p1.first, merge(newNode, p2.second));
            } else {
                long zeroInd = ZeroTreap.next(ZeroTreap.root, x);
                if (zeroInd != Long.MAX_VALUE) {
                    ZeroTreap.delete(zeroInd);
                    root = merge(merge(p1.first, newNode), merge(p2.first, p2.second));
                    delete((int)zeroInd);
                } else {
                    root = merge(merge(p1.first, newNode), merge(p2.first, p2.second));
                }
            }
        }

        public void inOrder(Node cur, ArrayList<Integer> sb) {
            if (cur == null) return;
            inOrder(cur.left, sb);
            sb.add(cur.value);
            inOrder(cur.right, sb);
        }

    }

    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nm = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = nm[0];
            int m = nm[1];
            int[] segment = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ImplicitTreap treap = new ImplicitTreap();
            for (int i = 1; i <= m; i++) {
                treap.insertKey(i, 0, true);
                ZeroTreap.insert(i);
            }
            for (int i = 0; i < n; i++) {
                treap.insertKey(segment[i] - 1, i + 1, false);
            }
            ArrayList<Integer> sb = new ArrayList<>();
            treap.inOrder(treap.root, sb);
            int i = sb.size();
            while (i > 0) {
                if (sb.get(i - 1) != 0) {
                    break;
                }
                i--;
            }
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                bw.write(i + "\n");
                String out = sb.subList(0, i).toString();
                out = out.substring(1, out.length() - 1).replaceAll("[ +]", "");
                out = out.replaceAll(",", " ");
                bw.write(out);
            } catch (IOException ignored) {
            }
        } catch (IOException ignored) {
        }
    }
}
