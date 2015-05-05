package districoll.test;

import java.util.ArrayList;

import districoll.collection.IDistriIterator;
import districoll.collection.IDistriList;
import districoll.collection.DistriList;
import junit.framework.TestCase;

public class TestDistriList extends TestCase {
	public void testNormalCreate() throws InterruptedException {
		ArrayList<String> al = new ArrayList<String>(50000000);
		for (int i = 0; i < 50000000; i++) {
			al.add("hello" + i);
		}
	}

	
	public void testDistriCreate() throws InterruptedException {
		IDistriList<String> l = new DistriList<String>("hello");
		for (int i = 0; i < 50000000; i++) {
			l.add("hello" + i);
		}
	}

	public void testDistriGet() {
		IDistriList<String> l = new DistriList<String>("hello");
		System.out.println(l.size());
		IDistriIterator<String> it = l.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
