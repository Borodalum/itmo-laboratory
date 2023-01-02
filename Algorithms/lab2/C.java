import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue que = new Queue(n);
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int command = sc.nextInt();
            if (command == 1) {
                que.pushBack(sc.nextInt());
            } else if (command == 2) {
                que.popFront();
            } else if (command == 3) {
                que.popBack();
            } else if (command == 4) {
                sb.append(que.frontSize(sc.nextInt()));
                if (n != 1) {
                    sb.append(System.lineSeparator());
                }
            } else {
                sb.append(que.firstElem());
                if (n != 1) {
                    sb.append(System.lineSeparator());
                }
            }
            n--;
        }
        System.out.println(sb);
    }
}

final class Queue {
    private final int[] queueArray;
    private final int[] front;
    private int start;
    private int end;
    public Queue(int n) {
        queueArray = new int[n];
        front = new int[100_000 + 1];
        start = 0;
        end = 0;
    }
    public void pushBack(int id) {
        queueArray[end] = id;
        front[id] = end - start;
        end++;
    }
    public void popFront() {
        for (int i = start; i < end; i++) {
            front[queueArray[i]] = front[queueArray[i]] - 1;
        }
        start++;
    }
    public void popBack() {
        end--;
    }
    public int frontSize(int q) {
        return front[q];
    }
    public int firstElem() {
        return queueArray[start];
    }
}
