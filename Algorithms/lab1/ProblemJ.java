import java.util.Scanner;
public class ProblemJ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int sumV = 0;
        int leftV = 0;
        int sumW = 0;
        int leftW = 1;
        int[] v = new int[n];
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            sumV += v[i];
            sumW += w[i];
        }
    }
}
