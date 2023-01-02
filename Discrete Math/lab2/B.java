package lab1;

import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        ArrayList<String> cycleTable = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            String cur = str.substring(i) + str.substring(0, i);
            cycleTable.add(cur);
        }
        cycleTable.sort((s1, s2) -> {
            if(s1.length() != s2.length())
                return s1.length() - s2.length();
            else
                return s1.compareTo(s2);
        });
        StringBuilder ans = new StringBuilder();
        for (String it : cycleTable) {
            ans.append(it.charAt(it.length() - 1));
        }
        System.out.println(ans);
    }
}
