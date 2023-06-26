import java.util.Arrays;
import java.util.Scanner;

public class ProblemC {
    public static long count = 0;
    public static void merge(int[] arr, int[] a1, int[] a2) {
        int i = 0, j = 0, k = 0;
        while (i < a1.length && j < a2.length) {
            if (a1[i] <= a2[j]) {
                arr[k++] = a1[i++];
            } else {
                arr[k++] = a2[j++];
                count += a1.length - i;
            }
        }
        while (i < a1.length) {
            arr[k++] = a1[i++];
        }
        while (j < a2.length) {
            arr[k++] = a2[j++];
        }
    }
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length/2;
        int[] a1 = Arrays.copyOfRange(arr, 0, mid);
        int[] a2 = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(a1);
        mergeSort(a2);

        merge(arr, a1, a2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        mergeSort(arr);
        System.out.println(count);
    }
}
