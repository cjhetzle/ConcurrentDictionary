package main.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * @Author cjhetzle
 */
class MergeSort {

	/*
	 * 
	 */
	public static Map<String, Integer> sort(List<Entry<String, Integer>> unsortedList) {
		if (unsortedList == null || unsortedList.size() < 2)
			return null;
		
		Map<String, Integer> contentMap = new HashMap<String, Integer>();
		sort(unsortedList, 0, unsortedList.size()-1, contentMap);
		
		return contentMap;
	}
	
	/*
	 * A recursive method which will halve the list until
	 * it is down to 1 unit lists. It will then call a merge
	 * function to combine.
	 * 
	 * O(NLogN)
	 */
	private static void sort(List<Entry<String, Integer>> unsortedSegment, int i, int j, Map<String, Integer> map) {
		if (i < j) {
			

            int m = i + (j - i) / 2;
            
            sort(unsortedSegment, i, m, map);
            sort(unsortedSegment, m+1, j, map);
            
            merge(unsortedSegment, i, m, j);
		} else {
			map.put(unsortedSegment.get(i).getKey(),
					unsortedSegment.get(i).getValue());
		}
	}
	
	private static void merge(List<Entry<String, Integer>> unsortedSegment, int l, int m, int r) {
		
		int lenL = m - l + 1;
		int lenR = r - m;
		
		List<Entry<String, Integer>> _L = new ArrayList<Entry<String, Integer>>();
		List<Entry<String, Integer>> _R = new ArrayList<Entry<String, Integer>>();
		for (int i = 0; i < lenL; i++) {
			_L.add(unsortedSegment.get(l+i));
		}
		for (int i = 0; i < lenR; i++) {
			_R.add(unsortedSegment.get(m+i+1));
		}
		
        int i = 0, j = 0, k = l;
        
        while (i < lenL && j < lenR) {
            if (_L.get(i).getValue() <= _R.get(j).getValue()) {
                unsortedSegment.set(k, _L.get(i));
                i++;
            }
            else {
                unsortedSegment.set(k, _R.get(j));
                j++;
            }
            k++;
        }
        
        while (i < lenL) {
            unsortedSegment.set(k, _L.get(i));
            i++;
            k++;
        }
 
        // Copy remaining elements of R[] if any
        while (j < lenR) {
            unsortedSegment.set(k, _R.get(j));
            j++;
            k++;
        }
	}
}
