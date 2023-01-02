package lab1;

import java.util.Scanner;
public class A {
    public static int[][] compose(int[][] rel1, int[][] rel2) {
        int[][] compRels = new int[rel1.length][rel2.length];
        for(int i = 0; i < rel1.length; i++) {
            for(int j = 0; j < rel2.length; j++) {
                if (rel1[i][j] == 1) {
                    for (int k = 0; k < rel2.length; k++) {
                        if (rel2[j][k] == 1) {
                            compRels[i][k] = 1;
                        }
                    }
                }
            }
        }
        return compRels;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] allRels1 = new int[n][n];
        int[][] allRels2 = new int[n][n];
        int[] relations1 = new int[]{1, 1, 1, 1, 1};
        int[] relations2 = new int[]{1, 1, 1, 1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                allRels1[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                allRels2[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if (i == j) {
                    if (allRels1[i][j] == 1) {
                        relations1[0] = relations1[0] == 1 ? 1 : 0;
                        relations1[1] = 0;
                    } else {
                        relations1[0] = 0;
                        relations1[1] = relations1[1] == 1 ? 1 : 0;
                    }
                    if (allRels2[i][j] == 1) {
                        relations2[0] = relations2[0] == 1 ? 1 : 0;
                        relations2[1] = 0;
                    } else {
                        relations2[0] = 0;
                        relations2[1] = relations2[1] == 1 ? 1 : 0;
                    }
                }
                if (allRels1[i][j] == 1) {
                    if (allRels1[j][i] == 0) {
                        relations1[2] = 0;
                    } else {
                        if (i != j) {
                            relations1[3] = 0;
                        }
                    }
                    for (int k = 0; k < n; k++) {
                        if (allRels1[j][k] == 1) {
                            if (allRels1[i][k] == 0) {
                                relations1[4] = 0;
                                break;
                            }
                        }
                    }
                }
                if (allRels2[i][j] == 1) {
                    if (allRels2[j][i] == 0) {
                        relations2[2] = 0;
                    } else {
                        if (i != j) {
                            relations2[3] = 0;
                        }
                    }
                    for (int k = 0; k < n; k++) {
                        if (allRels2[j][k] == 1) {
                            if (allRels2[i][k] == 0) {
                                relations2[4] = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(relations1[i]);
            if (i != 4) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print(relations2[i]);
            if (i != 4) {
                System.out.print(" ");
            }
        }
        System.out.println();
        int[][] rels = compose(allRels1, allRels2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(rels[i][j]);
                if (j != n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
