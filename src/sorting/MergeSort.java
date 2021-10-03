package sorting;

import java.util.Arrays;

class MergeSort {
    static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int[] first = Arrays.copyOfRange(array,0,array.length/2);
        int[] second = Arrays.copyOfRange(array,array.length/2,array.length);
        return merge(mergeSort(first),mergeSort(second));
    }

    private static int[] merge(int[]a, int[]b){
        int aCounter =0, bCounter = 0, resCounter = 0;
        int[] result = new int[a.length+b.length];
        while (aCounter<a.length && bCounter<b.length){
            if(a[aCounter]<b[bCounter]){
                result[resCounter++]=a[aCounter++];
            }else {
                result[resCounter++]=b[bCounter++];
            }
        }
        while (aCounter<a.length){
            result[resCounter++]=a[aCounter++];
        }
        while (bCounter<b.length){
            result[resCounter++]=b[bCounter++];
        }
        return result;
    }
}
