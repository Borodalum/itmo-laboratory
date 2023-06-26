import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskC {

    private static class Pair {
        int to;
        String aChar;

        public Pair(int to, String aChar) {
            this.to = to;
            this.aChar = aChar;
        }
    }

    private static long cnt(int cur, boolean[] hah, Set<Integer> pass, HashMap<Integer, ArrayList<Pair>> trans) {
        if (!hah[cur]) return 0;
        long cure = 0;
        if (pass.contains(cur)) cure += 1;
        for (Pair s : trans.getOrDefault(cur, new ArrayList<>())) {
            cure = (cure + cnt(s.to, hah, pass, trans)) % 1_000_000_007;
        }
        return cure;
    }

    private static void dfs(HashMap<Integer, ArrayList<Integer>> trans, boolean[] visited, boolean[] hah,
                            int cur) {
        if (hah[cur] || visited[cur]) return;
        visited[cur] = true;
        hah[cur] = true;
        for (Integer s : trans.getOrDefault(cur, new ArrayList<>())) {
            dfs(trans, visited, hah, s);
        }
        visited[cur] = false;
    }

    private static boolean haveCycle(HashMap<Integer, ArrayList<Pair>> trans, boolean[] visited, boolean[] hah,
                                  int cur) {
        if (!hah[cur]) return false;
        if (visited[cur]) return true;
        visited[cur] = true;
        for (Pair s : trans.getOrDefault(cur, new ArrayList<>())) {
            if (haveCycle(trans, visited, hah, s.to)) {
                visited[cur] = false;
                return true;
            }
        }
        visited[cur] = false;
        return false;
    }

    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new FileReader("problem3.in"))) {
            int[] nmk = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Set<Integer> passStates = Arrays.stream(bf.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toSet());
            HashMap<Integer, ArrayList<Pair>> transitions = new HashMap<>();
            HashMap<Integer, ArrayList<Integer>> reversedTrans = new HashMap<>();
            for (int i = 0; i < nmk[1]; i++) {
                String[] line = bf.readLine().split(" ");
                int from = Integer.parseInt(line[0]);
                int to = Integer.parseInt(line[1]);
                ArrayList<Pair> trans = transitions.getOrDefault(from, new ArrayList<>());
                trans.add(new Pair(to, line[2]));
                transitions.put(from, trans);
                ArrayList<Integer> rrrr = reversedTrans.getOrDefault(to, new ArrayList<>());
                rrrr.add(from);
                reversedTrans.put(to, rrrr);
            }
            boolean[] visited = new boolean[nmk[0] + 1];
            boolean[] hah = new boolean[nmk[0] + 1];
            for (int i : passStates) {
                dfs(reversedTrans, visited, hah, i);
            }
            long ans;
            if (haveCycle(transitions, visited, hah, 1)) {
                ans = -1;
            } else {
                ans = cnt(1, hah, passStates, transitions);
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("problem3.out"))) {
                bw.write(String.valueOf(ans));
                bw.flush();
            }
        } catch (IOException ignored) {
        }
    }
}
