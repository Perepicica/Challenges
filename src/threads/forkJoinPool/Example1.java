package threads.forkJoinPool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class Example1 {
    //quick sort
    //recursive action

    public static void main(String[] args) {
        int[] arr = ThreadLocalRandom.current().ints(50, 0, 100).toArray();
        System.out.println("Before: " + Arrays.toString(arr));

        ForkJoinPool executor = new ForkJoinPool(3);
        executor.invoke(new ForkJoinQuickSortAction(arr));
        System.out.println("After: " + Arrays.toString(arr));
        executor.shutdown();
    }
}

class ForkJoinQuickSortAction extends RecursiveAction {

    private static final int THRESHOLD = 5;
    private final int[] data;
    private final int fromInclusive;
    private final int toInclusive;

    ForkJoinQuickSortAction(int[] data) {
        this(data, 0, data.length - 1);
    }

    private ForkJoinQuickSortAction(int[] data, int fromInclusive, int toInclusive) {
        this.data = data;
        this.fromInclusive = fromInclusive;
        this.toInclusive = toInclusive;
    }

    @Override
    protected void compute() {
        if (toInclusive - fromInclusive < THRESHOLD) {
            System.out.println(Thread.currentThread());
            Arrays.sort(data, fromInclusive, toInclusive + 1);
        } else {
            int pivot = partition(data, fromInclusive, toInclusive);
            ForkJoinQuickSortAction left = new ForkJoinQuickSortAction(data, fromInclusive, pivot);
            ForkJoinQuickSortAction right = new ForkJoinQuickSortAction(data, pivot + 1, toInclusive);

            invokeAll(left, right);
        }
    }

    private int partition(int[] array, int fromInclusive, int toInclusive) {
        int pivot = array[fromInclusive];
        int i = fromInclusive - 1;
        int j = toInclusive + 1;
        while (true) {
            do {
                ++i;
            } while (array[i] < pivot);
            do {
                j--;
            } while (array[j] > pivot);
            if (i >= j) {
                return j;
            }
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
