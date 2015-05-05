package districoll.collection;

import java.util.List;

import districoll.core.IDistributeObject;
import districoll.core.MetaAnchor;
import districoll.core.MetaLoader;
import districoll.core.PartitionAnchor;
import districoll.core.PartitionLoader;
import districoll.core.ISplit;

public class DistriList<E> implements IDistributeObject, IDistriList<E> {
	String name;
	ISplit split;
	MetaLoader<E> metaLoader;
	PartitionLoader partitionLoader;

	public DistriList(String name) {
		this.name = name;
		this.partitionLoader = new PartitionLoader();
		this.metaLoader = new MetaLoader<E>(name);
		this.split = metaLoader.getMeta().getSplit();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void destory() {
		metaLoader.delMeta();
	}

	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long size() {
		return metaLoader.getMeta().getSumSplitSize();
	}

	@Override
	public IDistriIterator<E> iterator() {
		// 镜像当时的partition列表
		PartitionIterator<E> pi = new PartitionIterator<E>(metaLoader.getMeta()
				.getPartAnchors());
		return pi;
	}

	@Override
	public void clear() {
		destory();
	}

	@Override
	public MetaAnchor getMetaAnchor() {
		return metaLoader.getMeta();
	}

	@Override
	public void add(E e) {
		split.addObject(e);
		if (split.needSplit()) {
			PartitionAnchor pa = metaLoader.newPartition();
			partitionLoader.refreshPartitionData(pa, split.split());
		}
	}

	@Override
	public void remove(E e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAll(List<E> all) {
		for (E e : all) {
			add(e);
		}
	}

	@Override
	public void removeAll(List<E> all) {
		// TODO Auto-generated method stub

	}

}
