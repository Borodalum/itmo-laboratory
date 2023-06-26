import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskG {

    private static class Pair {
        int to;
        String aChar;

        public Pair(int to, String aChar) {
            this.to = to;
            this.aChar = aChar;
        }
    }

    private static class PairP {
        int to;
        int to2;

        public PairP(int to, int to2) {
            this.to = to;
            this.to2 = to2;
        }
    }

    private static boolean isTerminal(int v, Set[] passStates, int n) {
        return passStates[n].contains(v);
    }
    private static int get(String alph, int n, HashMap<Integer, ArrayList<Pair>>[] transitions, int v) {
        for (Pair p1 : transitions[n].getOrDefault(v, new ArrayList<>())) {
            if (p1.aChar.equals(alph)) {
                return p1.to;
            }
        }
        return 0;
    }

    private static boolean bfs(boolean[] vis1, boolean[] vis2,
                               HashMap<Integer, ArrayList<Pair>>[] transitions, Set[] passStates, ArrayList<String> alphabet) {
        PairP p = new PairP(1, 1);
        Deque<PairP> queue = new ArrayDeque<>();
        queue.push(p);
        while (!queue.isEmpty()) {
            PairP cur = queue.removeFirst();
            if (cur.to != 0) {
                vis1[cur.to] = true;
            }
            if (cur.to2 != 0) {
                vis2[cur.to2] = true;
            }
            if (isTerminal(cur.to, passStates, 0) != isTerminal(cur.to2, passStates, 1)) {
                return false;
            }
            for (String alpha : alphabet) {
                int newU = get(alpha, 0, transitions, cur.to);
                int newV = get(alpha, 1, transitions, cur.to2);
                if (!vis1[newU] || !vis2[newV]) {
                    if (newU != 0 || newV != 0) {
                        queue.push(new PairP(newU, newV));
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("equivalence.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("equivalence.out"));

        ArrayList<String> alphabet = new ArrayList<>();
        for (char i = 'a'; i <= 'z'; i++) {
            alphabet.add(String.valueOf(i));
        }
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
        boolean[] vis1 = new boolean[nmk[0][0] + 1];
        Arrays.fill(vis1, false);
        boolean[] vis2 = new boolean[nmk[1][0] + 1];
        Arrays.fill(vis2, false);
        if (bfs(vis1, vis2, transitions, passStates, alphabet)) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        bw.flush();
        bw.close();
    }
}
