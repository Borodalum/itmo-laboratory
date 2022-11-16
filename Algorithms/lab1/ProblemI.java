import java.util.Scanner;
public class ProblemI {
    private static int Vp;
    private static int Vf;
    private static double a;
    public static double f(double x) {
        return Math.sqrt(x*x + (1-a)*(1-a)) / (double)Vp
                + Math.sqrt(a*a + (1-x)*(1-x)) / (double)Vf;
    }
    public static double ternarySearch() {
        double left = 0.0;
        double right = 1.0;
        while(right - left > 0.00001) {
            double b = left + (right - left)/3;
            double c = right - (right - left)/3;
            if (f(b) > f(c)) {
                left = b;
            } else {
                right = c;
            }
        }

        return left;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vp = sc.nextInt();
        Vf = sc.nextInt();
        a = Double.parseDouble(sc.next());

        System.out.println(ternarySearch());
    }
}
