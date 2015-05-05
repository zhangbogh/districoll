package districoll.collection;

import java.util.List;

public interface IDistriList<E> {
	// 添加
	public void add(E e);

	// 删除
	public void remove(E e);

	// 包含
	public boolean contains(E e);

	// 添加一批
	public void addAll(List<E> all);

	// 删除一批
	public void removeAll(List<E> all);

	// 列表的大小
	public long size();

	// 迭代访问器
	public IDistriIterator<E> iterator();

	// 清理
	public void clear();
}
