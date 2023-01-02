//package lab2;

import java.util.Scanner;

public class N {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        dp[0][0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                int phi;
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    phi = 0;
                } else {
                    phi = 1;
                }
                dp[i][j] = min(dp[i - 1][j - 1] + phi, dp[i][j - 1] + 1, dp[i - 1][j] + 1);
            }
        }
        System.out.println(dp[a.length()][b.length()]);
    }
    public static int min(int a, int b, int c) {
        return Math.min(Math.min(Math.min(a, b), Math.min(b, c)), Math.min(Math.min(a, b), Math.min(a, c)));
    }

}
