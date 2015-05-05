package districoll.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeapPartitionStore implements IPartitionStore {
	private Map<String, Partition> map = new ConcurrentHashMap<String, Partition>();

	@Override
	public void putPartition(String id, Partition part) {
		map.put(id, part);
	}

	@Override
	public Partition getPartition(String id) {
		return map.get(id);
	}

	@Override
	public void removePartition(String id) {
		map.remove(id);
	}
}
