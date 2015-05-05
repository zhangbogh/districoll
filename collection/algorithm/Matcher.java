package districoll.collection.algorithm;

import districoll.collection.IDistriList;

public interface Matcher<E, F, G> {
	public String getFirstKey(E e);

	public String getSecondKey(F f);

	// 执行单行的计算动作
	public IDistriList<G> match(IDistriList<E> le, IDistriList<E> lf);
}
