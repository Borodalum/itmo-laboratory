package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] dp = new int[n];
        int[] price = new int[n];
        for (int i = 1; i < n - 1; i++) {
            price[i] = sc.nextInt();
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        int[] trace = new int[n];
        MaxQueue mq = new MaxQueue();
        mq.pushBack(price[0], 1);
        for (int i = 1; i < n; i++) {
            dp[i] = mq.getMax() + price[i];
            if (trace[count] != mq.getMaxIndex()) {
                count++;
                trace[count] = mq.getMaxIndex();
                sb.append(mq.getMaxIndex()).append(" ");
            }
            mq.pushBack(dp[i], i + 1);

            if (mq.getSize() > k) {
                mq.popFront();
            }
        }
        System.out.println(dp[n - 1]);
        System.out.println(count);
        System.out.println(sb + "" + n);
    }
}

final class MaxQueue {
    private final MaxStack st1;
    private final MaxStack st2;
    private int mqSize = 0;

    public MaxQueue() {
        st1 = new MaxStack();
        st2 = new MaxStack();
    }

    public void pushBack(int x, int index) {
        st2.push(x, index);
        mqSize++;
    }

    public int[] popFront() {
        if (st1.getSize() == 0) {
            while (st2.getSize() != 0) {
                int[] temp = st2.pop();
                st1.push(temp[0], temp[1]);
            }
        }
        mqSize--;
        return st1.pop();
    }

    public int getMax() {
        return Math.max(st2.getMax()[0], st1.getMax()[0]);
    }

    public int getMaxIndex() {
        if (st2.getMax()[0] > st1.getMax()[0]) {
            return st2.getMax()[1];
        } else {
            return st1.getMax()[1];
        }
    }

    public int getSize() {
        return mqSize;
    }

}

final class MaxStack {
    private final List<int[]> stackArray = new ArrayList<>();
    private final List<int[]> maxStack = new ArrayList<>();

    public MaxStack() {
    }

    public void push(int elem, int index) {
        stackArray.add(new int[] {elem, index});
        if (maxStack.size() == 0) {
            maxStack.add(new int[] {elem, index});
        } else {
            if (maxStack.get(maxStack.size() - 1)[0] < elem) {
                maxStack.add(new int[] {elem, index});
            } else {
                maxStack.add(maxStack.get(maxStack.size() - 1));
            }
        }
    }

    public int[] pop() {
        maxStack.remove(maxStack.size() - 1);
        return stackArray.remove(stackArray.size() - 1);
    }

    public int[] getMax() {
        if (maxStack.size() == 0) {
            return new int[] {Integer.MIN_VALUE, -1};
        } else {
            return maxStack.get(maxStack.size() - 1);
        }
    }

    public int getSize() {
        return stackArray.size();
    }
}

