package sorting;

class QuickSort {
    static int[] quickSort(int[] array){
        int begin = 0;
        int end = array.length-1;
        return sort(array,begin,end);
    }

    private static int[] sort(int[] array,int begin,int end){
        if(begin<end) {
            int index = partition(array, begin, end);
            sort(array, begin, index - 1);
            sort(array, index + 1, end);
        }
        return array;
    }

    private static int partition(int[] array,int begin,int end){
        int tmp;
        int pivot = array[begin];
        while(begin<end){
            if(array[begin]>array[end]){
                tmp = array[begin];
                array[begin] = array[end];
                array[end] = tmp;
            }
            if(array[begin] == pivot)  end--; else begin++;
        }
        return end;
    }
}
