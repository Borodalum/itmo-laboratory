
import java.util.ArrayList;
import java.util.Scanner;

public class Eshka {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        ArrayList<String> alph = new ArrayList<String>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alphabet.length(); i++) {
            alph.add(Character.toString(alphabet.charAt(i)));
        }
        StringBuilder res = new StringBuilder();
        int i = 0;
        String t = "";
        while(i < n.length()) {
            char c = n.charAt(i);
            String tc = t + c;
            if (alph.contains(tc)) {
                t = tc;
            } else {
                res.append(alph.indexOf(t)).append(" ");
                alph.add(tc);
                t = Character.toString(c);
            }
            i++;
        }
        res.append(alph.indexOf(t));
        System.out.println(res.toString());
    }
}
