import java.net.Inet4Address;
import java.util.Arrays;
import java.util.Scanner;
public class A {
    public static void merge(long[] arr, long[] a1, long[] a2) {
        int i = 0, j = 0, k = 0;
        while (i < a1.length && j < a2.length) {
            if (a1[i] >= a2[j]) {
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
    public static void mergeSort(long[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length/2;
        long[] a1 = Arrays.copyOfRange(arr, 0, mid);
        long[] a2 = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(a1);
        mergeSort(a2);

        merge(arr, a1, a2);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        mergeSort(a);
        int i = n - 1;
        long bits = 0;

        while (i > 0) {
            a[i] += a[i-1];
            bits += a[i];
            mergeSort(a);
            i--;
        }
        System.out.println(bits);
    }
}
