package main.dictionary;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Random;

public class ConcurrentDictionary implements Dictionary {
	
	private int min, max, size;
	
	// Map is used as it is not thread safe, so it makes a good test
	Map<String, Integer> dict = new HashMap<String, Integer>();
	List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>();

	@Override
	public synchronized void Insert(String key, int value) {
		Entry<String, Integer> e = new SimpleEntry<String, Integer>(key, value);
		
		int index = BinarySearch.search(list, value);
		list.add(index, e);
		dict.put(key, value);
		size++;
		
		if (value > max)
			max = value;
		else if (value < min)
			min = value;
		
	}

	@Override
	public int Search(String key) {
		synchronized(dict) {
			return dict.containsKey(key) ? dict.get(key) : -1;
		}
	}

	@Override
	public synchronized void Delete(String key) {
		if (!dict.containsKey(key))
			return;
		int index;
		{
			int val = dict.get(key);
			index = BinarySearch.search(list, val);
		}
		
		dict.remove(key);
		list.remove(index);
		size--;
		
		if (size > 0) {
			if (min != list.get(0).getValue())
				min = list.get(0).getValue();
			else if (max != list.get(size-1).getValue())
				max = list.get(size-1).getValue();
		}
	}

	@Override
	public int Size() {
		// 0(1)
		return size;
	}

	@Override
	public int Min() {
		// O(1)
		return min;
	}

	@Override
	public int Max() {
		// O(1)
		return max;
	}

	@Override
	public synchronized void Build(List<Entry<String, Integer>> input) {
		if (input != null && input.size() > 1) {
			dict = MergeSort.sort(input);
		}
		list = input;
		
		size = list.size();
		min = list.get(0).getValue();
		max = list.get(size-1).getValue();
	}

	@Override
	public synchronized void DeleteRandom(Random rand) {
		if (size == 0) return;
		int index = rand.nextInt(size);
		String key = list.get(index).getKey();
		Delete(key);
	}

}
