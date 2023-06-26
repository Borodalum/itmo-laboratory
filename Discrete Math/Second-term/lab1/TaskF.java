import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskF {
    private static class Pair {
        int to;
        String aChar;

        public Pair(int to, String aChar) {
            this.to = to;
            this.aChar = aChar;
        }
    }

    private static int getT2(HashMap<Integer, ArrayList<Pair>>[] transitions, String cha, int v) {
        for (Pair p2 : transitions[1].getOrDefault(v, new ArrayList<>())) {
            if (p2.aChar.equals(cha)) {
                return p2.to;
            }
        }
        return -1;
    }

    private static boolean dfs(int u, int v, boolean[] visited, HashMap<Integer, Integer> assocs,
                               HashMap<Integer, ArrayList<Pair>>[] transitions, Set[] passStates) {
        visited[u] = true;
        if ((passStates[0].contains(u) && !passStates[1].contains(v))
                || (!passStates[0].contains(u) && passStates[1].contains(v))) {
            return false;
        }
        assocs.put(u, v);
        boolean res = true;
        int t1 = -1;
        for (Pair p1 : transitions[0].getOrDefault(u, new ArrayList<>())) {
            t1 = p1.to;
            int t2 = getT2(transitions, p1.aChar, v);
            if (t2 == -1) return false;
            if (visited[t1]) {
                res = res && (t2 == assocs.getOrDefault(t1, -1));
            } else {
                res = res && (dfs(t1, t2, visited, assocs, transitions, passStates));
            }
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("equivalence.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("equivalence.out"));

        int[][] nmk = new int[2][];
        nmk[0] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Set[] passStates = new Set[2];
        passStates[0] = Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toSet());
        HashMap<Integer, ArrayList<Pair>>[] transitions = new HashMap[2];
        transitions[0] = new HashMap<Integer, ArrayList<Pair>>();
        transitions[1] = new HashMap<Integer, ArrayList<Pair>>();
        for (int i = 0; i < nmk[0][1]; i++) {
            String[] line = bf.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            ArrayList<Pair> trans = transitions[0].getOrDefault(from, new ArrayList<>());
            trans.add(new Pair(to, line[2]));
            transitions[0].put(from, trans);
        }
        nmk[1] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        passStates[1] = Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toSet());
        for (int i = 0; i < nmk[1][1]; i++) {
            String[] line = bf.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            ArrayList<Pair> trans = transitions[1].getOrDefault(from, new ArrayList<>());
            trans.add(new Pair(to, line[2]));
            transitions[1].put(from, trans);
        }
        bf.close();
        if (nmk[0][0] != nmk[1][0] || nmk[0][2] != nmk[1][2]) {
            bw.write("NO");
        } else {
            boolean[] visited = new boolean[nmk[0][0] + 1];
            HashMap<Integer, Integer> associations = new HashMap<>();
            if (dfs(1, 1, visited, associations, transitions, passStates)) {
                bw.write("YES");
            } else {
                bw.write("NO");
            }
            bw.flush();
        }
        bw.close();
    }
}

