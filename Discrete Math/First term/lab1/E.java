package lab1;

import java.util.Scanner;
public class E {
    public static int[] makePoly(int[] vec) {
        int[][] tempPolynom = new int[vec.length][vec.length];
        for (int i = 0; i < vec.length; i++) {
            tempPolynom[i][0] = vec[i];
        }
        for (int i = 1; i < vec.length; i++) {
            for (int j = 0; j < vec.length - 1; j++) {
                tempPolynom[j][i] = (tempPolynom[j + 1][i - 1] + tempPolynom[j][i - 1]) % 2;
            }
        }
        int[] out = new int[vec.length];
        System.arraycopy(tempPolynom[0], 0, out, 0, vec.length);
        return out;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int len = (int)Math.pow(2, n);
        int[] vec = new int[len];
        String[] vec1 = new String[len];
        while (len > 0) {
            vec1[vec1.length - len] = sc.next();
            vec[vec.length - len] = sc.nextInt();
            len--;
        }
        int[] out = makePoly(vec);
        for (int i = 0; i < out.length; i++) {
            System.out.println(vec1[i] + " " + out[i]);
        }
    }
}
