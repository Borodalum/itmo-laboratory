import java.util.Scanner;
public class ProblemH {
    public static double f(double x) {
        return (double)(Math.sqrt(x) + Math.pow(x, 2));
    }
    public static double realBinarySearch(double c) {
        double left = 0.0;
        double x = 1.0;
        while (f(x) < c) {
            x = x*2.0;
        }
        double right = x;
        while (right - left > 0.000001) {
            double mid = (right + left)/2;
            if (f(mid) > c) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double c = Double.parseDouble(sc.next());
        System.out.println(realBinarySearch(c));
    }
}
