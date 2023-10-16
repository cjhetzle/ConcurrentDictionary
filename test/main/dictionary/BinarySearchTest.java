package main.dictionary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BinarySearchTest {
	
	static List<Entry<String, Integer>> sortedList;
	static int listSize = 10;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		sortedList = new ArrayList<Entry<String, Integer>>();
		
		System.out.println("Creating SORTED LIST of size " + listSize + ".");
		
		SimpleEntry<String, Integer> en;
		
		for (int i = 0; i < listSize; i++) {
			en = new SimpleEntry<String, Integer>("test" + i, i);
			sortedList.add(en);
			
			System.out.println('(' + en.getKey() +
					", " + en.getValue() + ')');
		}
		
		System.out.println("+++++++++++++\n");
	}

	@AfterAll
	static void tearDown() throws Exception {
		System.out.println("\n----> Finished BinarySearch Testing.");
	}

	@Test
	void testSearch() {
		
		Random random = new Random();
		int searchFor;
		
		for (int i = 0; i < 5; i++) {
			searchFor = random.nextInt(listSize);
			
			int searchResult = BinarySearch.search(sortedList, searchFor);
			
			System.out.println("Searching list for value " + searchFor + " BinarySearch found " + searchResult);
			
			if (searchResult != searchFor)
				fail("Did not find the correct index position of the list.");
		}
	}

}
