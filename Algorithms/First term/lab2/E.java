package lab2;

import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 100;
        BaseStack stack = new BaseStack(n);
        while (sc.hasNext()) {
            String exp = sc.next();
            switch (exp) {
                case "+" -> {
                    int o1 = stack.pop();
                    int o2 = stack.pop();
                    stack.push(o1 + o2);
                }
                case "-" -> {
                    int o1 = stack.pop();
                    int o2 = stack.pop();
                    stack.push(o2 - o1);
                }
                case "*" -> {
                    int o1 = stack.pop();
                    int o2 = stack.pop();
                    stack.push(o1 * o2);
                }
                case "/" -> {
                    int o1 = stack.pop();
                    int o2 = stack.pop();
                    stack.push(o1 / o2);
                }
                default -> {
                    int elem = Integer.parseInt(exp);
                    stack.push(elem);
                }
            }
        }
        System.out.println(stack.pop());
        sc.close();
    }
}

final class BaseStack {
    private final int[] stackArray;
    private int stackSize = 0;
    public BaseStack(int n) {
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
}
