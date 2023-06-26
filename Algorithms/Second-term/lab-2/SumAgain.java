import java.io.*;
import java.util.Arrays;

public class SumAgain {
    private static class TreapWithSums {
        private static class Node {
            long value;
            int priority;
            int count;
            long sum;
            Node left;
            Node right;

            public Node() {
            }

            public Node(long x, int y) {
                value = x;
                priority = y;
                count = 1;
                sum = value;
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

        private long getSum(Node cur) {
            return cur == null ? 0 : cur.sum;
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
            r.sum = getSum(r.left) + getSum(r.right) + r.value;
        }

        private Pair split(Node r, long x0) {
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

        public void add(long x) {
            Node newNode = new Node(x, getPriority());
            Pair p1 = split(root, x - 1);
            Pair p2 = split(p1.second, x);
            if (p2.first == null) {
                root = merge(p1.first, merge(newNode, p2.second));
            } else {
                root = merge(p1.first, merge(p2.first, p2.second));
            }
        }

        public long sum(int l, int r) {
            Pair p1 = split(root, l - 1);
            Pair p2 = split(p1.second, r);
            long ans = p2.first == null ? 0 : p2.first.sum;
            root = merge(p1.first, merge(p2.first, p2.second));
            return ans;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).sum();
            long lastQST = 0;
            int mod = 1_000_000_000;
            StringBuilder ans = new StringBuilder();
            TreapWithSums treap = new TreapWithSums();
            for (int i = 0; i < n; i++) {
                String[] line = bf.readLine().split(" ");
                if (line[0].equals("+")) {
                    treap.add((lastQST + Integer.parseInt(line[1])) % mod);
                    lastQST = 0;
                } else {
                    long answ = treap.sum(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                    ans.append(answ).append("\n");
                    lastQST = answ;
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                bw.write(ans.toString());
            } catch (IOException ignored) {
            }
        } catch (IOException ignored) {
        }
    }
}
