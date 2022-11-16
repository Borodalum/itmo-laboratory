import java.util.Scanner;
public class ProblemK {
    public static boolean isPossible(int[] a, long mS, int k) {
        int count = 0;
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > mS)
                return false;
            long curSum = sum + a[i];
            if (curSum <= mS) {
                sum = curSum;
            } else {
                sum = a[i];
                count++;
            }
        }
        return count <= k - 1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long left = 0;
        long right = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            right += arr[i];
        }
        while (right - 1 > left) {
            long mid = (left + right)/2;
            if (isPossible(arr, mid, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(right);
    }
}
