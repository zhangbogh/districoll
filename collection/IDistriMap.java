package districoll.collection;

import java.util.Iterator;

public interface IDistriMap<K, V> {
	// ���
	public boolean put(K k, V v);

	// ��ȡ
	public V get(K k);

	// ����
	public boolean containsKey(K k);

	// ����������
	public Iterator<KeyValuePair<K, V>> iterator();
}
