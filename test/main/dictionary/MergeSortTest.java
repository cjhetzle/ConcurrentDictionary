package main.dictionary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MergeSortTest {
	
	static List<Entry<String, Integer>> unsortedList = new ArrayList<Entry<String, Integer>>();
	static Map<String, Integer> contentMap;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Entry<String, Integer> en;
		int r;
		Random random = new Random();
		
		System.out.println("Creating UNSORTED LIST.");
		
		for (int i = 0; i < 10; i++) {
			r = random.nextInt(1000);
			
			en = new SimpleEntry<String, Integer>("test" + r, r);
			unsortedList.add(en);
			
			System.out.println('(' + en.getKey() +
					", " + en.getValue() + ')');
		}
		
		System.out.println("+++++++++++++\n");
	}

	@AfterAll
	static void tearDown() throws Exception {
		System.out.println("\n----> Finished MergeSort Testing.");
	}

	@Test
	void sortTest() {
		contentMap = MergeSort.sort(unsortedList);
		
		for (int i = 0; i < unsortedList.size(); i++) {
			System.out.println('(' + unsortedList.get(i).getKey() +
					", " + unsortedList.get(i).getValue() + ')');
			
			if (i + 1 < unsortedList.size() && 
					unsortedList.get(i).getValue() > unsortedList.get(i+1).getValue())
				fail("List was not sorted in ASC.");
			
			if (!contentMap.containsKey(unsortedList.get(i).getKey()))
				fail("ContentMap did not contain a certain key.");
		}
	}

}
