package sorting;

class InsertionSort {
    static int[] insertionSort(int[] array){
        int tmp;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >0 && array[j]<array[j-1] ; j--) {
                tmp = array[j];
                array[j] = array[j-1];
                array[j-1] = tmp;
            }
        }
        return array;
    }
}
