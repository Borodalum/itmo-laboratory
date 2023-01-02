import java.util.Scanner;

public class ProblemB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numArr = new int[101];
        for (int i = 0; i < n; i++) {
            numArr[sc.nextInt()]++;
        }
        for (int i = 0; i < numArr.length; i++) {
            while (numArr[i] > 0) {
                System.out.print(i + " ");
                numArr[i]--;
            }
        }
    }
}
