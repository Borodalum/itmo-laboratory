import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class C {
    static class Scan {
        BufferedReader bufReader;
        StringTokenizer stToken;
        Scan() {
            bufReader = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {
            while (stToken == null || !stToken.hasMoreElements()) {
                try {
                    stToken = new StringTokenizer(bufReader.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return stToken.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
    private static int dfs(int i, int[][] refs) {
        if (refs[i].length == 0) {
            return 0;
        }
        int depth = 0;
        for (int j = 0; j < refs[i].length; j++) {
            depth = Math.max(dfs(refs[i][j] - 1, refs), depth);
        }
        return depth + 1;
    }
    public static void main(String[] args) {
        final Scan sc = new Scan();
        short n = (short)sc.nextInt();
        int k = 0;
        final int[][] vars = new int[n][32];
        int[][] refs = new int[n][];
        while (n > 0) {
            final short m = (short)sc.nextInt();
            if (m == 0) {
                k++;
                refs[refs.length - n] = new int[0];
                n--;
                continue;
            }
            final short vec = (short)Math.pow(2, m);
            refs[refs.length - n] = new int[m];
            for (int i = 0; i < m; i++) {
                refs[refs.length - n][i] = sc.nextInt();
            }
            for (int i = 0; i < vec; i++) {
                vars[vars.length - n][i] = sc.nextInt();
            }
            n--;
        }
        StringBuilder result = new StringBuilder();
        final int deep = dfs(refs.length - 1, refs);
        result.append(deep).append('\n');
        StringBuilder getValue = new StringBuilder();
        final int[] results = new int[refs.length];
        for (int i = 0; i < (int)Math.pow(2, k); i++) {
            String bitI = Integer.toBinaryString(i);
            bitI = "0".repeat(k - bitI.length()) + bitI;
            short l = 0;
            for (int c = 0; c < refs.length; c++) {
                if (refs[c].length == 0) {
                    results[c] = Character.getNumericValue(bitI.charAt(l++));
                    continue;
                }
                for (int o = 0; o < refs[c].length; o++) {
                    getValue.append(results[refs[c][o] - 1]);
                }
                final short inCase = Short.parseShort(getValue.toString(), 2);
                getValue.setLength(0);
                results[c] = vars[c][inCase];
            }
            result.append(results[results.length - 1]);
        }
        System.out.println(result.toString());
    }
}
