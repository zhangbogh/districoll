package districoll.collection.algorithm;

import java.util.List;

public interface Grouper<E> {
	public String groupKey(E e);

	public E group(List<E> l);
}
