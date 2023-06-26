//package lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class L {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    dp[i] = Math.max(dp[j], dp[i]);
                }
            }
            dp[i]++;
        }

        int mx = -1;
        int curMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[i] > curMax) {
                curMax = dp[i];
                mx = i;
            }
        }
        System.out.println(curMax);
        List<Integer> sb = new ArrayList<>();
        for (int i = mx; i >= 0; i--) {
            if (curMax > 0 && dp[i] == curMax) {
                sb.add(a[i]);
                curMax--;
            }
        }
        for (int i = 0; i < sb.size(); i++) {
            System.out.print(sb.get(sb.size() - i - 1) + " ");
        }
    }
}
