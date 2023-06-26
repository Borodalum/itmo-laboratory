import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class CarnotaurusParent {
    private static ArrayList<ArrayList<Integer>> jumps;
    private static ArrayList<Integer> moves;
    private static ArrayList<Integer> d;

    private static int lca(int u, int v) {
        if (d.get(v) > d.get(u)) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        int trueLg = (int)(Math.log(jumps.size()) / Math.log(2) + 1) - 1;
        for (int i = trueLg; i >= 0; i--) {
            if (d.get(jumps.get(u).get(i)) - d.get(v) >= 0) {
                u = jumps.get(u).get(i);
            }
        }
        if (v == u) {
            return v;
        }
        for (int i = trueLg; i >= 0; i--) {
            if (!Objects.equals(jumps.get(v).get(i), jumps.get(u).get(i))) {
                v = jumps.get(v).get(i);
                u = jumps.get(u).get(i);
            }
        }
        return jumps.get(v).get(0);
    }

    private static int find(int v) {
        if (moves.get(v) == v) return v;
        moves.set(v, find(moves.get(v)));
        return moves.get(v);
    }

    private static void fill(int size, int s) {
        for (int i = 0; i <= size; i++) {
            jumps.get(s).add(0);
        }
    }
    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int m = Integer.parseInt(bf.readLine());
            int lg = (int) (Math.log(m) / Math.log(2) + 1);
            jumps = new ArrayList<>();
            moves = new ArrayList<>();
            d = new ArrayList<>();
            jumps.add(new ArrayList<>());
            fill(lg, 0);
            d.add(0);
            moves.add(0);
            int size = 0;
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                for (int i = 0; i < m; i++) {
                    String[] op = bf.readLine().split(" ");
                    switch (op[0]) {
                        case "+" -> {
                            int cur = Integer.parseInt(op[1]);
                            cur--;
                            size++;
                            jumps.add(new ArrayList<>());
                            fill(lg, size);
                            moves.add(size);
                            d.add(d.get(cur) + 1);
                            jumps.get(size).set(0, cur);
                            for (int j = 1; j <= lg; j++) {
                                jumps.get(size).set(j, jumps.get(jumps.get(size).get(j - 1)).get(j - 1));
                            }
                        }
                        case "-" -> {
                            int cur = Integer.parseInt(op[1]);
                            cur--;
                            moves.set(cur, jumps.get(cur).get(0));
                        }
                        case "?" -> {
                            int a = Integer.parseInt(op[1]);
                            int b = Integer.parseInt(op[2]);
                            a--;
                            b--;
                            bw.write((find(lca(a, b)) + 1) + "\n");
                        }
                    }
                }
            }
        } catch (IOException ignored) {}
    }
}
