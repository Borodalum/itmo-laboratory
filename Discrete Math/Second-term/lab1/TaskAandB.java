import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskAandB {
    private static class Pair {
        int to;
        String aChar;

        public Pair(int to, String aChar) {
            this.to = to;
            this.aChar = aChar;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new FileReader("problem2.in"))) {
            String word = bf.readLine();
            int[] nmk = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Set<Integer> passStates = Arrays.stream(bf.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toSet());
            HashMap<Integer, ArrayList<Pair>> transitions = new HashMap<>();
            for (int i = 0; i < nmk[1]; i++) {
                String[] line = bf.readLine().split(" ");
                int from = Integer.parseInt(line[0]);
                int to = Integer.parseInt(line[1]);
                ArrayList<Pair> trans = transitions.getOrDefault(from, new ArrayList<>());
                trans.add(new Pair(to, line[2]));
                transitions.put(from, trans);
            }
            Set<Integer> currentStates = new HashSet<>();
            currentStates.add(1);
            for (int i = 0; i < word.length(); i++) {
                Set<Integer> tmp = new HashSet<>();
                for (Integer cur : currentStates) {
                    ArrayList<Pair> state = transitions.getOrDefault(cur, new ArrayList<>());
                    for (Pair curPair : state) {
                        if (curPair.aChar.equals(String.valueOf(word.charAt(i)))) {
                            tmp.add(curPair.to);
                        }
                    }
                }
                currentStates = tmp;
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("problem2.out"))) {
                if (!Collections.disjoint(passStates, currentStates)) {
                    bw.write("Accepts");
                } else {
                    bw.write("Rejects");
                }
                bw.flush();
            } catch (IOException ignored) {
            }
        } catch (IOException ignored) {
        }
    }
}
