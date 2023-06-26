import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LCA {
    private static void dfs(HashMap<Integer, ArrayList<Integer>> tree, int cur, int dept, HashMap<Integer, Integer> depth) {
        for (int i : tree.getOrDefault(cur, new ArrayList<>())) {
            depth.put(i, dept + 1);
            dfs(tree, i, dept + 1, depth);
        }
    }

    private static int lca(int u, int v, HashMap<Integer, Integer> depths, int[][] dp, int n, HashMap<Integer, Integer> p) {
        if (depths.get(v) > depths.get(u)) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        for (int i = (int)(Math.log(n)/Math.log(2)); i >= 0; i--) {
            if (depths.get(dp[u][i]) - depths.get(v) >= 0) {
                u = dp[u][i];
            }
        }
        if (v == u) {
            return v;
        }
        for (int i = (int)(Math.log(n)/Math.log(2)); i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        return p.get(v);
    }
    public static void main(String[] args) {
        HashMap<Integer, Integer> parent = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> tree = new HashMap<>();
        HashMap<Integer, Integer> depth = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
            parent.put(1, 1);
            for (int i = 2; i <= n; ++i) {
                int curParent = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
                parent.put(i, curParent);
                ArrayList<Integer> childs = tree.getOrDefault(curParent, new ArrayList<>());
                childs.add(i);
                tree.put(curParent, childs);
            }
            depth.put(1, 0);
            dfs(tree, 1, 0, depth);
            int[][] dp = new int[n + 1][(int)(Math.log(n)/Math.log(2) + 1)];
            for (int i = 1; i <= n; i++) {
                dp[i][0] = parent.get(i);
            }
            for (int j = 1; j <= (int)(Math.log(n)/Math.log(2)); j++) {
                for (int i = 1; i <= n; i++) {
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
                }
            }
            int m = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                for (int i = 0; i < m; i++) {
                    int[] curParent = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    bw.write(lca(curParent[0], curParent[1], depth, dp, n, parent) + "\n");
                }
            }
        } catch (IOException ignored) {}
    }
}
