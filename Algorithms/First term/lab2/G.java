//package lab2;

import java.util.Scanner;

public class G {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        DSU dsu = new DSU(n);
        while(sc.hasNext()) {
            String c = sc.next();
            if (c.equals("union")) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                dsu.union(a, b);
            } else if (c.equals("get")) {
                System.out.println(dsu.get(sc.nextInt()));
            }
        }
    }
}

final class DSU {
    int[] parent;
    int[] rangs;
    int[] count;
    int[] min;
    int[] max;
    public DSU(int n) {
        parent = new int[n + 1];
        rangs = new int[n + 1];
        count = new int[n + 1];
        min = new int[n + 1];
        max = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
            rangs[i] = 1;
            count[i] = 1;
            min[i] = i;
            max[i] = i;
        }
    }
    private int find(int a) {
        if (parent[a] == a)
            return a;
        parent[a] = find(parent[a]);
        return parent[a];
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
            count[y] += count[x];
            min[y] = Math.min(min[x], min[y]);
            max[y] = Math.max(max[x], max[y]);
        }
        if (rangs[x] == rangs[y]) {
            rangs[y]++;
        }
    }
    public String get(int x) {
        x = find(x);
        return min[x] + " " + max[x] + " " + count[x];
    }
    private int[] swap(int x, int y) {
        int temp = x;
        x = y;
        y = temp;
        return new int[]{x, y};
    }
}
