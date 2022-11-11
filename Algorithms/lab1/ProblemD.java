import java.util.Scanner;
import java.lang.Math;

public class ProblemD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        NewHeap heap = new NewHeap(n);
        while (n > 0) {
            if (sc.nextInt() == 0) {
                heap.insert(sc.nextInt());
            } else {
                System.out.println(heap.extract());
            }
            n--;
        }
    }
}
class NewHeap {
    private final int[] heapArray;
    private int size = 0;
    public NewHeap(int n) {
        heapArray = new int[2 * n];
    }
    public void insert(int x) {
        heapArray[size] = x;
        siftUp(size);
        size++;
        //System.out.println(Arrays.toString(heapArray));
    }
    public int extract() {
        int out = heapArray[0];
        if (size >= 1) {
            swap(0, size - 1);
            heapArray[size - 1] = 0;
            size--;
            siftDown(0);
        }
        //System.out.println(Arrays.toString(heapArray));
        return out;
    }
    private int getParent(int index) {
        if (index == 0)
            return 0;
        return (int) Math.floor((index - 1) / 2);
    }
    private int getLeftChild(int index) {
        return 2 * index + 1;
    }
    private int getRightChild(int index) {
        return 2 * index + 2;
    }
    private void swap(int x, int y) {
        int temp = heapArray[x];
        heapArray[x] = heapArray[y];
        heapArray[y] = temp;
    }
    public void siftUp(int index) {
        int parent = getParent(index);
        if (heapArray[index] > heapArray[parent]) {
            swap(parent, index);
            siftUp(parent);
        }
    }
    public void siftDown(int index) {
        int leftChIndex = getLeftChild(index);
        int rightChIndex = getRightChild(index);
        if (size - 1 < leftChIndex)
            return;
        int left = heapArray[leftChIndex];
        if (size - 1 < rightChIndex) {
            if (left > heapArray[index]) {
                swap(leftChIndex, index);
                siftDown(leftChIndex);
            }
        }
        int right = heapArray[rightChIndex];
        if (left > right) {
            if (left > heapArray[index]) {
                swap(leftChIndex, index);
                siftDown(leftChIndex);
            }
        } else {
            if (right > heapArray[index]) {
                swap(rightChIndex, index);
                siftDown(rightChIndex);
            }
        }
    }
}
