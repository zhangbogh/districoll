package districoll.collection.algorithm;

import java.util.Comparator;

import districoll.collection.IDistriList;

public interface DistrListAlgorithm<E, F, G> {
	public IDistriList<E> sort(IDistriList<E> src, Comparator<E> compare);

	public E max(IDistriList<E> src, Comparator<E> compare);

	public E min(IDistriList<E> src, Comparator<E> compare);

	public IDistriList<E> uniq(IDistriList<E> src, Comparator<E> compare);

	public IDistriList<E> group(IDistriList<E> src, Grouper<E> compare);

	public IDistriList<G> match(IDistriList<E> lFirst, IDistriList<F> lSecond,
			Matcher<E, F, G> match);
}
