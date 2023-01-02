import java.util.ArrayList;
import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> alph = new ArrayList<String>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alphabet.length(); i++) {
            alph.add(Character.toString(alphabet.charAt(i)));
        }
        StringBuilder res = new StringBuilder();
        int lastCode = sc.nextInt();
        String t = alph.get(lastCode);
        res.append(t);
        for (int i = 1; i < n; i++) {
            String s;
            int code = sc.nextInt();
            if (alph.size() > code) {
                s = alph.get(code);
            } else {
                s = alph.get(lastCode);
                s += t;
            }
            res.append(s);
            t = Character.toString(s.charAt(0));
            alph.add(alph.get(lastCode) + t);
            lastCode = code;
        }
        System.out.println(res.toString());
    }
}
