package sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {
    private int [] array = {1,7,99,56,8,9,8,123,11111,765,9,0,87};
    private int [] sortedArray = {1,7,99,56,8,9,8,123,11111,765,9,0,87};
    private int[] oneEl = {1};
    private int[] threeEl = {3,1,2};
    private int[] threeElSorted = {1,2,3};
    private int[] emptyArr ={};

    private int[] forCounting={1,1,1,2,3,5,4,5,4,5,6,3,4,5,2,1,1,1,6,4,6,1,1};
    private int[] forCountingSorted={1,1,1,2,3,5,4,5,4,5,6,3,4,5,2,1,1,1,6,4,6,1,1};

    @Test
    void bubbleSortTest(){
        Arrays.sort(sortedArray);
        assertArrayEquals(sortedArray,BubbleSort.bubbleSort(array));
        assertArrayEquals(emptyArr,BubbleSort.bubbleSort(emptyArr));
        assertArrayEquals(oneEl,BubbleSort.bubbleSort(oneEl));
        assertArrayEquals(threeElSorted,BubbleSort.bubbleSort(threeEl));

    }

    @Test
    void mergeSortTest(){
        Arrays.sort(sortedArray);
        assertArrayEquals(sortedArray,MergeSort.mergeSort(array));
        assertArrayEquals(emptyArr,MergeSort.mergeSort(emptyArr));
        assertArrayEquals(oneEl,MergeSort.mergeSort(oneEl));
        assertArrayEquals(threeElSorted,MergeSort.mergeSort(threeEl));
    }

    @Test
    void countSortTest(){
        Arrays.sort(forCountingSorted);
        assertArrayEquals(forCountingSorted,CountSort.countSort(forCounting));
    }

    @Test
    void insertionSortTest(){
        Arrays.sort(sortedArray);
        assertArrayEquals(sortedArray,InsertionSort.insertionSort(array));
        assertArrayEquals(emptyArr,InsertionSort.insertionSort(emptyArr));
        assertArrayEquals(oneEl,InsertionSort.insertionSort(oneEl));
        assertArrayEquals(threeElSorted,InsertionSort.insertionSort(threeEl));
    }

    @Test
    void quickSortTest(){
        Arrays.sort(sortedArray);
       assertArrayEquals(sortedArray,QuickSort.quickSort(array));
        assertArrayEquals(emptyArr,QuickSort.quickSort(emptyArr));
        assertArrayEquals(oneEl,QuickSort.quickSort(oneEl));
        assertArrayEquals(threeElSorted,QuickSort.quickSort(threeEl));
    }
}