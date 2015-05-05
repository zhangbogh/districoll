package districoll.collection;

import java.util.List;

public interface IDistriList<E> {
	// ���
	public void add(E e);

	// ɾ��
	public void remove(E e);

	// ����
	public boolean contains(E e);

	// ���һ��
	public void addAll(List<E> all);

	// ɾ��һ��
	public void removeAll(List<E> all);

	// �б�Ĵ�С
	public long size();

	// ����������
	public IDistriIterator<E> iterator();

	// ����
	public void clear();
}
