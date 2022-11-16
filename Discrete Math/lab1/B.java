import java.util.Scanner;
public class B {
    // work
    public static boolean f0(char[] vec) {
        if (vec[0] == '1') {
            return false;
        }
        return true;
    }
    // work
    public static boolean f1(char[] vec) {
        if (vec[vec.length - 1] == '0') {
            return false;
        }
        return true;
    }
    //work
    public static boolean fm(char[] vec) {
        if (vec.length <= 1)
            return true;
        int half = vec.length/2;
        for (int i = 0; i < half; i++) {
            if (vec[i] > vec[i + half]) {
                return false;
            }
        }
        char[] f = new char[half];
        char[] s = new char[vec.length - half];
        for (int i = 0; i < half; i++) {
            f[i] = vec[i];
            s[i] = vec[half + i];
        }
        return fm(f) && fm(s);
    }
    // work
    public static boolean fl(char[] vec, int n) {
        int[][] tempPolynom = new int[vec.length][vec.length];
        for (int i = 0; i < vec.length; i++) {
            tempPolynom[i][0] = Character.getNumericValue(vec[i]);
        }
        for (int i = 1; i < vec.length; i++) {
            for (int j = 0; j < vec.length - 1; j++) {
                tempPolynom[j][i] = (tempPolynom[j + 1][i - 1] + tempPolynom[j][i - 1]) % 2;
            }
        }
        for (int i = 0; i < vec.length; i++) {
            if (Integer.bitCount(i) > 1 && tempPolynom[0][i] == 1) {
                return false;
            }
        }
        return true;
    }
    // work
    public static boolean fs(char[] vec) {
        if (vec.length == 1)
            return false;
        for (int i = 0; i < vec.length; i++) {
            if (vec[i] == vec[vec.length - i - 1]) {
                return false;
            }
        }
        return true;
    }
    public static void answer(boolean[] vec) {
        if (vec[0] || vec[1] || vec[2] || vec[3] || vec[4]) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] postCs = new boolean[]{true, true, true, true, true}; //01MLS
        while (n > 0) {
            int x = sc.nextInt();
            char[] vec = sc.next().toCharArray();
            postCs[0] = postCs[0] && f0(vec);
            postCs[1] = postCs[1] && f1(vec);
            postCs[2] = postCs[2] && fm(vec);
            postCs[3] = postCs[3] && fl(vec, x);
            postCs[4] = postCs[4] && fs(vec);
            n--;
        }
        answer(postCs);
    }
}
