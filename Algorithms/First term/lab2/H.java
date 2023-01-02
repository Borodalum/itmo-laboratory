package lab2;

import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        WeightDSU dsu = new WeightDSU(n);
        while (m > 0) {
            String c = sc.next();
            if (c.equals("add")) {
                dsu.addWeight(sc.nextInt(), sc.nextInt());
            } else if (c.equals("join")) {
                dsu.union(sc.nextInt(), sc.nextInt());
            } else if (c.equals("get")) {
                System.out.println(dsu.getWeight(sc.nextInt()));
            }
            m--;
        }
    }
}

final class WeightDSU {
    int[] parent;
    int[] rangs;
    int[] weight;

    public WeightDSU(int n) {
        parent = new int[n + 1];
        rangs = new int[n + 1];
        weight = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
            rangs[i] = 1;
            weight[i] = 0;
        }
    }

    private int find(int a) {
        if (parent[a] == a)
            return a;
        return find(parent[a]);
    }

    private int findWeight(int a, int res) {
        if (parent[a] == a)
            return res + weight[a];
        return findWeight(parent[a], res + weight[a]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (rangs[y] < rangs[x]) {
            int[] temp = swap(x, y);
            x = temp[0];
            y = temp[1];
        }
        if (parent[x] != parent[y]) {
            parent[x] = y;
            weight[x] -= weight[y];
        }
        if (rangs[x] == rangs[y]) {
            rangs[y]++;
        }
    }

    private int[] swap(int x, int y) {
        return new int[]{y, x};
    }

    public void addWeight(int x, int w) {
        weight[find(x)] += w;
    }

    public int getWeight(int x) {
        return findWeight(x, 0);
    }
}
