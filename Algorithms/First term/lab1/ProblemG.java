import java.util.Scanner;

public class ProblemG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int left = 0;
        int right = n * Math.max(x, y);
        while(left < right) {
            int mid = (right + left)/2;
            if ((mid / x + mid / y) < n - 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(right + Math.min(x, y));
    }
}
