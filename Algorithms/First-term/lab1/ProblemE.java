import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ProblemE {

    public static void merge(int[] arr, int[] a1, int[] a2) {
        int i = 0, j = 0, k = 0;
        while (i < a1.length && j < a2.length) {
            if (a1[i] <= a2[j]) {
                arr[k++] = a1[i++];
            } else {
                arr[k++] = a2[j++];
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
    private static int binarySearchLeft(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (high + low)/2;
            if (k > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    private static int binarySearchRight(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (high + low)/2;
            if (k < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        mergeSort(arr);
        int k = sc.nextInt();
        while (k > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int index1 = binarySearchLeft(arr, l);
            int index2 = binarySearchRight(arr, r);
            System.out.println(index2 - index1);
            k--;
        }
        sc.close();
    }
}
