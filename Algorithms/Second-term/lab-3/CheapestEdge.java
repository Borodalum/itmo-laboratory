import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CheapestEdge {
    private static HashMap<Integer, ArrayList<Integer>> tree;
    private static HashMap<Integer, Integer> depth;

    private static int lg;

    private static void dfs(int cur, int dept) {
        for (int i : tree.getOrDefault(cur, new ArrayList<>())) {
            depth.put(i, dept + 1);
            dfs(i, dept + 1);
        }
    }

    private static int lca(int u, int v, int[][] dp, int[][] mnJump) {
        if (depth.get(v) > depth.get(u)) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        int res = Integer.MAX_VALUE;
        for (int i = lg; i >= 0; i--) {
            if (depth.get(u) - (1 << i) >= depth.get(v)) {
                res = Math.min(res, mnJump[u][i]);
                u = dp[u][i];
            }
        }
        if (v == u) {
            return res;
        }
        for (int i = lg - 1; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                res = Math.min(res, Math.min(mnJump[u][i], mnJump[v][i]));
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        return Math.min(res, Math.min(mnJump[u][0], mnJump[v][0]));
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> parent = new HashMap<>();
        HashMap<Integer, Integer> edges = new HashMap<>();
        tree = new HashMap<>();
        depth = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bf.readLine());
            lg = (int)(Math.log(n) / Math.log(2));
            parent.put(1, 1);
            edges.put(1, Integer.MAX_VALUE);
            for (int i = 2; i <= n; ++i) {
                int[] info = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                parent.put(i, info[0]);
                edges.put(i, info[1]);
                ArrayList<Integer> childs = tree.getOrDefault(info[0], new ArrayList<>());
                childs.add(i);
                tree.put(info[0], childs);
            }
            depth.put(1, 0);
            dfs(1, 0);
            int[][] jumps = new int[n + 1][lg + 1];
            int[][] mnJumps = new int[n + 1][lg + 1];
            for (int i = 1; i <= n; i++) {
                jumps[i][0] = parent.get(i);
                mnJumps[i][0] = edges.get(i);
            }
            for (int j = 1; j <= lg; j++) {
                for (int i = 1; i <= n; i++) {
                    jumps[i][j] = jumps[jumps[i][j - 1]][j - 1];
                    mnJumps[i][j] = Math.min(mnJumps[i][j - 1], mnJumps[jumps[i][j - 1]][j - 1]);
                }
            }
            int m = Integer.parseInt(bf.readLine());
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                for (int i = 0; i < m; i++) {
                    int[] info = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    bw.write(lca(info[0], info[1], jumps, mnJumps) + "\n");
                }
            }
        } catch (IOException ignored) {}
    }
}
