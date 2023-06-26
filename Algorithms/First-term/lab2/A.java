package lab2;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        MyStack stack = new MyStack(n);
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int command = sc.nextInt();
            if (command == 1) {
                stack.push(sc.nextInt());
            } else if (command == 2) {
                stack.pop();
            } else {
                sb.append(stack.getMin());
                if (n != 1) {
                    sb.append(System.lineSeparator());
                }
            }
            n--;
        }
        System.out.println(sb);
    }
}

final class MyStack {
    private final int[] stackArray;
    private final int[] minInStack;
    private int minSize = 0;
    private int stackSize = 0;
    public MyStack(int n) {
        stackArray = new int[n];
        minInStack = new int[n];
    }
    public void push(int elem) {
        stackArray[stackSize] = elem;
        int lastMin;
        if (minSize != 0) {
            lastMin = minInStack[minSize - 1];
        } else {
            lastMin = Integer.MAX_VALUE;
        }
        minInStack[minSize] = Math.min(lastMin, elem);
        minSize++;
        stackSize++;
    }
    public void pop() {
        stackArray[stackSize] = Integer.MAX_VALUE;
        minInStack[minSize] = Integer.MAX_VALUE;
        minSize--;
        stackSize--;
    }
    public int getMin() {
        return minInStack[minSize - 1];
    }
}
