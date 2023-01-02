import java.util.Scanner;

public class ProblemF {
    private static int binarySearch(int[] arr, int k) {
        if (k < arr[0]) {
            return 0;
        }
        if (k > arr[arr.length - 1]) {
            return arr.length - 1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low + 1 < high) {
            int mid = (high + low)/2;
            if (arr[mid] > k) {
                high = mid;
            } else {
                low = mid;
            }
        }
        if (low != high) {
            if (Math.abs(arr[high] - k) < Math.abs(arr[low] - k)) {
                return high;
            } else {
                return low;
            }
        }
        return low;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for(int i = 0; i < k; i++) {
            int b = sc.nextInt();
            System.out.println(arr[binarySearch(arr, b)]);
        }
        sc.close();
    }
}
