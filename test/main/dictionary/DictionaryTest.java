package main.dictionary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author cjhetzle
 */
class DictionaryTest {
	
	static Dictionary dictionary;
	static List<Entry<String, Integer>> unsortedList;
	static int size = 100, max = 10000, min = -1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Setup at start.");
		dictionary = new ConcurrentDictionary();
		unsortedList = new ArrayList<Entry<String, Integer>>();
		
		Entry<String, Integer> en;
		int r;
		Random random = new Random();
		
		System.out.println("Creating UNSORTED LIST.");
		
		for (int i = 0; i < size-2; i++) {
			r = random.nextInt(max);
			
			en = new SimpleEntry<String, Integer>("test" + r, r);
			unsortedList.add(en);
		}
		
		unsortedList.add(new SimpleEntry<String, Integer>("test" + min, min));
		unsortedList.add(new SimpleEntry<String, Integer>("test" + max, max));
		List<Entry<String, Integer>> copy = new ArrayList<Entry<String, Integer>>();
		copy.addAll(unsortedList);
		// Let's build the dictionary
		dictionary.Build(copy);
		
		System.out.println("+++++++++++++\n");
	}

	@AfterAll
	static void tearDown() throws Exception {
		System.out.println("\\n----> Finished BinarySearch Testing.");
	}
	
	@AfterEach
	void buildDictionary() {
		List<Entry<String, Integer>> copy = new ArrayList<Entry<String, Integer>>();
		copy.addAll(unsortedList);
		dictionary.Build(copy);
	}

	@Test
	void testInsert() {
		//fail("Not implemented");
	}
	
	@Test
	void testSearch() {
		//fail("Not implemented");
	}
	
	@Test
	void testDelete() {
		dictionary.Delete(unsortedList.get(0).getKey());
		
		if (dictionary.Size() != size-1)
			fail("Size was not changed after deleting item.");
		
		if (dictionary.Search(unsortedList.get(0).getKey()) > 0)
			fail("Found item in dictionary after deleting.");
	}
	
	@Test
	void testSize() {
		int result = dictionary.Size();
		
		if (result != size)
			fail("Size was not found to be correct.\nWas looking for " +
					size + " but returned " + result);
	}
	
	@Test
	void testMin() {
		int result = dictionary.Min();
		
		if (result != min)
			fail("Min was not found to be correct.\nWas looking for " +
					min + " but returned " + result);
	}
	
	@Test
	void testMax() {
		int result = dictionary.Max();
		
		if (result != max)
			fail("Max was not found to be correct.\nWas looking for " +
					max + " but returned " + result);
	}
	

}
