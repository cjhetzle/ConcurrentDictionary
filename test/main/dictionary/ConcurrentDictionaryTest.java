package main.dictionary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author cjhetzle
 */
class ConcurrentDictionaryTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testReadWrite_Synchronized() {
		executeTask _t1 = DictionaryTask::SpamReadWrite_Task;
		Dictionary dict = new ConcurrentDictionary();
		DictionaryTask task = new DictionaryTask(dict, _t1);
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		List<Future<Void>> futures = new ArrayList<Future<Void>>();
		
		for (int i = 0; i < 10; i++) {
			futures.add(service.submit(task));
		}
		
		for (Future<Void> future : futures) {
			try {
				future.get();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail();
			}
		}
	}
	
	@Test
	void testReadWrite_Unsynchronized() {
		executeTask _t1 = DictionaryTask::SpamReadWrite_Task;
		Dictionary dict = new UnsynchronizedDictionary();
		DictionaryTask task = new DictionaryTask(dict, _t1);
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		List<Future<Void>> futures = new ArrayList<Future<Void>>();
		
		for (int i = 0; i < 10; i++) {
			futures.add(service.submit(task));
		}
		
		boolean areExceptions = false;
		for (Future<Void> future : futures) {
			try {
				future.get();
			} catch (Exception e) {
				areExceptions = true;
			}
		}
		
		if (!areExceptions)
			fail("No exceptions were run into. In other words, we didn't try hard enough.");
	}

}


class DictionaryTask implements Callable<Void> {
	
	Dictionary dict;
	Random random;
	executeTask task;
	
	public DictionaryTask(Dictionary dict, executeTask task) {
		this.dict = dict;
		random = new Random(System.currentTimeMillis());
		this.task = task;
	}

	@Override
	public Void call() throws Exception {
		task.execute(dict, random);
		return null;
	}
	
	public static void SpamReadWrite_Task(Dictionary dict, Random rand) throws InterruptedException {
		boolean isWrite = false;
		if (rand.nextDouble() > 0.5)
			isWrite = true;
		
		int actionCount = rand.nextInt(500, 1000);
		
		for (int i = 0; i < actionCount; i++) {
			if (isWrite) {
				addRandomEntry(dict, rand);
			} else {
				deleteRandomEntry(dict, rand);
			}
			
			Thread.sleep(1);
		}
	}
	
	private static void addRandomEntry(Dictionary dict, Random rand) {
		String key = "test-" +  rand.nextInt();
		int value = rand.nextInt();
		dict.Insert(key, value);
	}
	
	private static void deleteRandomEntry(Dictionary dict, Random rand) {
		dict.DeleteRandom(rand);
	}
	
}

interface executeTask {
	void execute(Dictionary dict, Random rand) throws InterruptedException;
}