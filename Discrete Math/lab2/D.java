import java.util.Scanner;
public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int i = 0;
        StringBuilder res = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        while (i < n.length()) {
            int ind = alphabet.indexOf(n.charAt(i));
            res.append(ind + 1).append(" ");
            sb.append(alphabet.charAt(ind)).append(alphabet.substring(0, ind)).append(alphabet.substring(ind + 1));
            alphabet = sb.toString();
            sb.setLength(0);
            i++;
        }
        System.out.println(res);
    }
}
