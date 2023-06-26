import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TreeWithoutLCA {
    private static final int LIMIT = 1_000_001;

    private static void dfs(int curV, HashSet<Integer>[] ways, int[] colors, ArrayList<ArrayList<Integer>> tree, int[] ans) {
        if (ways[curV] == null) {
            ways[curV] = new HashSet<>();
        }
        ways[curV].add(colors[curV]);
        for (int i = 0; i < tree.get(curV).size(); i++) {
            int b = tree.get(curV).get(i);
            dfs(b, ways, colors, tree, ans);
            if (ways[curV].size() < ways[b].size()) {
                HashSet<Integer> tmp = ways[curV];
                ways[curV] = ways[b];
                ways[b] = tmp;
            }
            ways[curV].addAll(ways[b]);
            ways[b].clear();
        }
        ans[curV] = ways[curV].size();
    }
    public static void main(String[] args) {
        int[] colors = new int[LIMIT];
        int[] ans = new int[LIMIT];
        HashSet[] ways = new HashSet[LIMIT];
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bf.readLine());
            for (int i = 0; i <= n; i++) {
                tree.add(new ArrayList<>());
            }
            for (int i = 1; i <= n; i++) {
                int[] info = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                tree.get(info[0]).add(i);
                colors[i] = info[1];
            }
            dfs(0, ways, colors, tree, ans);
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
                for (int i = 1; i <= n; i++) {
                    bw.write(ans[i] + " ");
                }
            }
        } catch (IOException ignored) {}
    }
}
