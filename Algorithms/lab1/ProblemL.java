import java.util.Scanner;
public class ProblemL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();

        long left = 1;
        long right = (long)n *n + 1;

        while (left < right) {
            long mid = (left + right)/2;
            if (check(mid, n) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left-1);
    }
    public static long check(long m, int n) {
        long a = 0;
        for (int i = 1; i <= n; ++i) {
            a += Math.min(n, (m-1)/i);
        }
        return a;
    }
}
