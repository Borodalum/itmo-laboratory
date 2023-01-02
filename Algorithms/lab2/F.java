package lab2;

import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int curElem = 1;
        BaStack b = new BaStack(n);
        BaStack a = new BaStack(n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = n - 1; i >= 0; i--) {
            a.push(arr[i]);
        }
        while (a.size() > 0) {
            b.push(a.pop());
            sb.append("push\n");
            if (b.atTop() == curElem) {
                b.pop();
                curElem++;
                sb.append("pop\n");
                while (b.size() > 0 && b.atTop() == curElem) {
                    b.pop();
                    curElem++;
                    sb.append("pop\n");
                }
            }
            if (a.size() == 0 && b.size() != 0 && b.atTop() != curElem) {
                sb.setLength(0);
                sb.append("impossible");
            }
        }
        System.out.println(sb);
    }
}

final class BaStack {
    private final int[] stackArray;
    private int stackSize = 0;
    public BaStack(int n) {
        stackArray = new int[n];
    }
    public void push(int elem) {
        stackArray[stackSize] = elem;
        stackSize++;
    }
    public int pop() {
        int temp = stackArray[--stackSize];
        stackArray[stackSize] = 0;
        return temp;
    }
    public int atTop() {
        return stackArray[stackSize - 1];
    }
    public int size() {
        return stackSize;
    }
}
