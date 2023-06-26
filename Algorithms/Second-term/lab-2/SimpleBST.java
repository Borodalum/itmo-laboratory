import java.util.Scanner;

public class SimpleBST {
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
    private static long prev(Node r, long x) {
        if (r == null) return Long.MIN_VALUE;
        if (r.value >= x) {
            return prev(r.left, x);
        } else {
            return Math.max(prev(r.right, x), r.value);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            while (sc.hasNext()) {
                String op = sc.next();
                long x = sc.nextLong();
                switch (op) {
                    case "insert" -> insert(x);
                    case "delete" -> delete(x);
                    case "exists" -> System.out.println(exists(x));
                    case "next" -> {
                        long value = next(root, x);
                        System.out.println(value == Long.MAX_VALUE ? "none" : value);
                    }
                    case "prev" -> {
                        long value = prev(root, x);
                        System.out.println(value == Long.MIN_VALUE ? "none" : value);
                    }
                }
            }
        } catch (IllegalStateException e) {}
    }
}
