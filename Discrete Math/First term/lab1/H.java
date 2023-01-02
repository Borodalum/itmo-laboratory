import java.util.Scanner;
public class H {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String o = "((A0|B0)|(A0|B0))";
        int i = 1;
        while (i < N) {
            o = "((" + o + "|((A" + i + "|A" + i + ")|" +
                    "(B" + i + "|B" + i + ")))|(A" + i + "|B" + i +"))";
            i++;
        }
        System.out.println(o);
    }
}
