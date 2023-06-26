import java.io.*;
import java.util.*;

public class Genealogy {
    private static HashMap<Integer, Integer> depth;
    private static HashMap<Integer, ArrayList<Integer>> tree;
    private static int lg;

    private static int globalTime = 0;

    private static void dfs(int cur, int dept, int[] tin) {
        tin[cur] = globalTime++;
        depth.put(cur, dept);
        for (int i : tree.getOrDefault(cur, new ArrayList<>())) {
            dfs(i, dept + 1, tin);
        }
    }

    private static int lca(int u, int v, int[][] jumps) {
        if (depth.get(v) > depth.get(u)) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        for (int i = lg; i >= 0; i--) {
            if (depth.get(jumps[u][i]) - depth.get(v) >= 0) {
                u = jumps[u][i];
            }
        }
        if (v == u) {
            return v;
        }
        for (int i = lg; i >= 0; i--) {
            if (jumps[v][i] != jumps[u][i]) {
                v = jumps[v][i];
                u = jumps[u][i];
            }
        }
        return jumps[v][0];
    }

    public static void main(String[] args) {
        depth = new HashMap<>();
        tree = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bf.readLine());
            lg = (int) (Math.log(n + 1) / Math.log(2));
            int[] info = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[][] jumps = new int[n + 1][lg + 1];
            for (int i = 1; i <= n; i++) {
                int curParent = Math.max(0, info[i - 1]);
                jumps[i][0] = curParent;
                ArrayList<Integer> childs = tree.getOrDefault(curParent, new ArrayList<>());
                childs.add(i);
                tree.put(curParent, childs);
            }
            int[] tin = new int[n + 1];
            globalTime = 0;
            dfs(0, -1, tin);
            for (int j = 1; j <= lg; j++) {
                for (int i = 1; i <= n; i++) {
                    jumps[i][j] = jumps[jumps[i][j - 1]][j - 1];
                }
            }
            int g = Integer.parseInt(bf.readLine());
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < g; i++) {
                String[] cur = bf.readLine().split(" ");
                int k = Integer.parseInt(cur[0]);
                ArrayList<Integer> clan = new ArrayList<>(
                        Arrays.stream(Arrays.copyOfRange(cur, 1, cur.length))
                                .mapToInt(Integer::parseInt)
                                .boxed()
                                .toList());
                clan.add(0);
                k++;
                clan.sort(Comparator.comparingInt((Integer a) -> tin[a]));
                int curAns = 0;
                for (int j = 1; j < k; j++) {
                    curAns += depth.get(clan.get(j - 1)) + depth.get(clan.get(j)) - 2 * depth.get(lca(clan.get(j - 1), clan.get(j), jumps));
                }
                curAns += depth.get(clan.get(0)) + depth.get(clan.get(k - 1)) - 2 * depth.get(lca(clan.get(0), clan.get(k - 1), jumps));
                ans.append(curAns / 2).append("\n");
            }
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                bw.write(ans.toString());
            }
        } catch (IOException ignored) {
        }
    }
}