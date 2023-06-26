import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class ProblemA {
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int qIndex = ThreadLocalRandom.current().nextInt(left, right + 1);
        int q = arr[qIndex];
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < q) { i++; }
            while (arr[j] > q) { j--; }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        quickSort(arr, left, j);
        quickSort(arr, i, right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        } 
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}