package lab2;

import java.util.Arrays;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.next().toCharArray();
        String[] str = new String[s.length];
        Arrays.fill(str, "");
        int j = s.length;
        StringBuilder sb = new StringBuilder();
        while (j > 0) {
            for (int i = 0; i < s.length; i++) {
                sb.append(s[i]).append(str[i]);
                str[i] = sb.toString();
                sb.setLength(0);
            }
            Arrays.sort(str);;
            j--;
        }
        System.out.println(str[0]);
    }
}
