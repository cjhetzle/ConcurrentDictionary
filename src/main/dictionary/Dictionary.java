package main.dictionary;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Requested API for Dictionary
 * 
 * @author cjhetzle
 */
public interface Dictionary {
	
		/*
		void Insert(string key, int value) - equal or better than O(logN)
		Description:
		Insert value with the provided key.
		*/
		public void Insert(String key, int value);
		
		/*
		int Search(string key) - equal or better than O(logN)
		Description:
		Return the value associated with the key.
		*/
		public int Search(String key);
		
		/*
		void Delete(string key) - equal or better than O(logN)
		Description:
		Delete the value associated with the key.
		*/
		public void Delete(String key);
		
		/*
		int Size() - O(1)
		Description:
		Return the size of the dictionary.
		*/
		public int Size();
		
		/*
		int Min() - O(1)
		Description:
		Return the min value.
		*/
		public int Min();
		
		/*
		int Max() - O(1)
		Description:
		Return the max value.
		*/
		public int Max();
		
		/*
		ConcurrentDictionary Build(List<Tuple<string,int>> input)- equal or better than O(NlogN)
		Description:
		Build Concurrent Dictionary object from the provided list.
		*/
		public void Build(List<Entry<String, Integer>> input);
		
		/**
		 * For testing purposes, delete a random item in the list
		 */
		public void DeleteRandom(Random rand);
		
		
		/* 
		Please note:
		    1. You can assume that amortized complexity is the same as worst case scenario complexity. So, O(1) amortized is the same as O(1).
		    2. You can assume that the string size is constant (so comparing string A to string B takes O(1)).
		    3. Assume the code you write is a production code.
		    4. If something is not clear or wasn’t mentioned, it is up to you to decide how you implement it.
		*/
}
