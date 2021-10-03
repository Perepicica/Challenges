package sorting;

import java.util.Map;
import java.util.TreeMap;

class CountSort {
    static int[] countSort(int[] array){
        Map<Integer,Integer> map = new TreeMap<>();
        for (int el:array) {
           map.compute(el, (w, prev) -> prev != null ? prev + 1 : 1);
        }
        int counter = 0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                array[counter++]=entry.getKey();
            }
        }
        return array;
    }
}
