import java.util.Scanner;

public class ProblemA {

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int qIndex = (low + high) / 2;
        int q = arr[qIndex];
        int i = low;
        int j = high;
        while (i <= j) {
            while (arr[i] < q) {i++;}
            while (arr[j] > q) {j--;}
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        quickSort(arr, low, j);
        quickSort(arr, i, high);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        } 
        sc.close();
        quickSort(arr, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }  
    }
}