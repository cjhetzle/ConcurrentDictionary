package main.dictionary;

import java.util.List;
import java.util.Map.Entry;

class BinarySearch {
	
	/**
	 * 
	 * @param sortedList
	 * @param value
	 * @return
	 */
    public static int search(List<Entry<String, Integer>> sortedList, Integer value) {
        int l = 0, r = sortedList.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
 
            Entry<String, Integer> _e = sortedList.get(m);
            
            if (_e.getValue().equals(value))
                return m;
 
            if (_e.getValue() < value)
                l = m + 1;
            else
                r = m - 1;
        }
 
        return l;
    }
}
