import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tree {
    static int lg;
    private static void dfs(HashMap<Integer, ArrayList<Integer>> tree, int cur, int dept, HashMap<Integer, Integer> depth) {
        for (int i : tree.getOrDefault(cur, new ArrayList<>())) {
            depth.put(i, dept + 1);
            dfs(tree, i, dept + 1, depth);
        }
    }

    private static int LCA(int u, int v, HashMap<Integer, Integer> depths, int[][] dp, int n, HashMap<Integer, Integer> p) {
        if (depths.get(v) > depths.get(u)) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        for (int i = lg; i >= 0; i--) {
            if (depths.get(dp[u][i]) - depths.get(v) >= 0) {
                u = dp[u][i];
            }
        }
        if (v == u) {
            return v;
        }
        for (int i = lg; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        return p.get(v);
    }

    private static void countOfColors(int root, HashMap<Integer, Integer> last, HashMap<Integer, Integer> color,
                                      HashMap<Integer, ArrayList<Integer>> tree, int[] ans, HashMap<Integer, Integer> depth,
                                      HashMap<Integer, Integer> p, int n, int[][] dp) {
        for (int i : tree.getOrDefault(root, new ArrayList<>())) {
            countOfColors(i, last, color, tree, ans, depth, p, n, dp);
            ans[root] += ans[i];
        }
        if (last.get(color.get(root)) != null) {
            ans[LCA(root, last.get(color.get(root)), depth, dp, n, p)]--;
        }
        last.put(color.get(root), root);
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> parent = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> tree = new HashMap<>();
        HashMap<Integer, Integer> depth = new HashMap<>();
        HashMap<Integer, Integer> color = new HashMap<>();
        int root = 0;
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bf.readLine());
            lg = (int)(Math.log(n) / Math.log(2));
            for (int i = 1; i <= n; ++i) {
                int[] info = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                parent.put(i, info[0]);
                color.put(i, info[1]);
                if (info[0] == 0) {
                    root = i;
                }
                ArrayList<Integer> childs = tree.getOrDefault(info[0], new ArrayList<>());
                childs.add(i);
                tree.put(info[0], childs);
            }
            depth.put(root, 0);
            dfs(tree, root, 0, depth);
            int[][] dp = new int[n + 1][lg + 1];
            for (int i = 1; i <= n; i++) {
                if (parent.get(i) == 0) {
                    dp[i][0] = root;
                } else {
                    dp[i][0] = parent.get(i);
                }
            }
            for (int j = 1; j <= lg; j++) {
                for (int i = 1; i <= n; i++) {
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
                }
            }
            int[] ans = new int[n + 1];
            Arrays.fill(ans, 1);
            HashMap<Integer, Integer> last = new HashMap<>();
            countOfColors(root, last, color, tree, ans, depth, parent, n, dp);
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                for (int i = 1; i <= n; i++) {
                    bw.write(ans[i] + " ");
                }
            }
        } catch (IOException ignored) {}
    }
}
