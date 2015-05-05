package districoll.collection;

import java.util.Iterator;

public interface IDistriMap<K, V> {
	// 添加
	public boolean put(K k, V v);

	// 获取
	public V get(K k);

	// 包含
	public boolean containsKey(K k);

	// 迭代访问器
	public Iterator<KeyValuePair<K, V>> iterator();
}
