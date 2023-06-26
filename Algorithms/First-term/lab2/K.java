//package lab2;

import java.util.Scanner;

public class K {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] dp = new int[n][m];
        int[][] price = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                price[i][j] = sc.nextInt();
            }
        }

        dp[0][0] = price[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + price[i][0];
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + price[0][i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j] + price[i][j];
                } else {
                    dp[i][j] = dp[i][j - 1] + price[i][j];
                }
            }
        }

        int a = n - 1;
        int b = m - 1;

        while (a > 0 && b > 0) {
            if (dp[a - 1][b] > dp[a][b - 1]) {
                sb.append("D");
                a--;
            } else {
                sb.append("R");
                b--;
            }
        }
        while (a > 0) {
            sb.append("D");
            a--;
        }
        while (b > 0) {
            sb.append("R");
            b--;
        }

        sb.reverse();
        System.out.println(dp[n - 1][m - 1]);
        System.out.println(sb);
    }
}
