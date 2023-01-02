package lab2;

import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Deque q1 = new Deque(n);
        Deque q2 = new Deque(n);

        while (n > 0) {
            String c = sc.next();
            if (c.equals("+")) {
                q2.pushBack(sc.nextInt());
            } else if (c.equals("*")) {
                q2.pushFront(sc.nextInt());
            } else {
                System.out.println(q1.popFront());
            }
            if (q1.size() < q2.size()) {
                q1.pushBack(q2.popFront());
            }
            n--;
        }
    }
}

final class Deque {
    private final int[] queueArr;
    private int end;
    private int start;
    public Deque(int n) {
        queueArr = new int[2*n];
        end = n;
        start = n;
    }
    public void pushBack(int q) {
        queueArr[end] = q;
        end++;
    }
    public void pushFront(int q) {
        start--;
        queueArr[start] = q;
    }
    public int popFront() {
        return queueArr[start++];
    }
    public int size() {
        return end - start;
    }
}