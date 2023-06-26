package lab2;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        MyMyStack stack = new MyMyStack(n);
        int ans = 0;

        stack.push(sc.nextInt(), 1);
        n--;

        while (n > 0) {
            int curBall = sc.nextInt();
            if (stack.atTop()[0] == curBall) {
                stack.push(curBall, stack.atTop()[1] + 1);
            } else {
                if (stack.atTop()[1] > 2) {
                    int b = stack.atTop()[0];
                    while (stack.atTop()[0] == b) {
                        stack.pop();
                        ans++;
                    }
                }
                int cnt = curBall == stack.atTop()[0] ? stack.atTop()[1] + 1: 1;
                stack.push(curBall, cnt);
            }
            n--;
        }
        if (stack.atTop()[1] > 2) {
            for (int i = 0; i < stack.atTop()[1]; i++) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
final class MyMyStack {
    private final int[][] stackArray;
    private int stackSize = 0;
    public MyMyStack(int n) {
        stackArray = new int[n][2];
    }
    public void push(int elem, int count) {
        stackArray[stackSize] = new int[] {elem, count};
        stackSize++;
    }
    public int pop() {
        int temp = stackArray[stackSize][0];
        stackArray[stackSize][0] = Integer.MAX_VALUE;
        stackArray[stackSize][1] = 0;
        stackSize--;
        return temp;
    }
    public int[] atTop() {
        return stackArray[stackSize - 1];
    }
    public int getSize() {
        return stackSize;
    }

}