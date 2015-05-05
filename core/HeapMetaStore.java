package districoll.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeapMetaStore implements IMetaStore {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4703130193386476614L;
	private Map<String, MetaAnchor> map = new ConcurrentHashMap<String, MetaAnchor>();

	@Override
	public void putMeta(String id, MetaAnchor part) {
		map.put(id, part);
	}

	@Override
	public MetaAnchor getMeta(String id) {
		return map.get(id);
	}

	@Override
	public void removeMeta(String id) {
		map.remove(id);
	}
}
