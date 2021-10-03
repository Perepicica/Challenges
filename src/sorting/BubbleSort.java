package sorting;

class BubbleSort {
    static int[] bubbleSort(int[] array) {
        int tmp;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < array.length; i++) {
                if (array[i - 1] > array[i]) {
                    tmp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = tmp;
                    isSorted = false;
                }
            }
        }
        return array;
    }
}
