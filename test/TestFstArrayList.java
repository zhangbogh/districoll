package districoll.test;

import java.util.ArrayList;
import java.util.List;

import org.nustaq.serialization.FSTConfiguration;

import junit.framework.TestCase;

public class TestFstArrayList extends TestCase {
	public void testArrayList() {
		ArrayList<User> l = new ArrayList<User>();
		User u = new User();
		u.age = 10;
		u.name = "zhang";
		u.data = new byte[] { (byte) 0xFC, (byte) 0xFA, (byte) 0xFB };
		l.add(u);
		u = new User();
		u.age = 11;
		u.name = "bo";
		u.data = new byte[] { (byte) 0xFD, (byte) 0xFE, (byte) 0xFF };
		l.add(u);
		FSTConfiguration cfg = FSTConfiguration.createDefaultConfiguration();
		cfg.registerClass(User.class);
		byte[] bs = cfg.asByteArray(l);

		@SuppressWarnings("unchecked")
		List<User> l1 = (List<User>) cfg.asObject(bs);
		
		assertEquals(l.get(0).age, l1.get(0).age);
		assertEquals(l.get(1).name, l1.get(1).name);
		assertEquals(l.get(0).data[0], l1.get(0).data[0]);
		assertEquals(l.get(0).data[1], l1.get(0).data[1]);
	}
}
