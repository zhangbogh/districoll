package districoll.collection;

import java.util.List;

import org.nustaq.serialization.FSTConfiguration;

import districoll.core.Partition;
import districoll.core.PartitionAnchor;
import districoll.core.PartitionLoader;

public class PartitionIterator<E> implements IDistriIterator<E> {
	java.util.Iterator<PartitionAnchor> it_pas;
	java.util.Iterator<E> it_datas;
	PartitionLoader pl;
	FSTConfiguration cfg = FSTConfiguration.getDefaultConfiguration();

	public PartitionIterator(List<PartitionAnchor> ancs) {
		this.pl = new PartitionLoader();
		this.it_pas = ancs.iterator();
	}

	@Override
	public boolean hasNext() {
		if (it_pas.hasNext()) {
			return true;
		} else if (it_datas.hasNext()) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E next() {
		if (it_datas == null) {
			PartitionAnchor pa = it_pas.next();
			Partition p = pl.loadPartitionData(pa);
			List<E> datas = (List<E>) cfg.asObject(p.getPartitionData());
			it_datas = datas.iterator();
			return it_datas.next();
		} else if (it_datas.hasNext()) {
			return it_datas.next();
		} else if (it_pas.hasNext()) {
			PartitionAnchor pa = it_pas.next();
			Partition p = pl.loadPartitionData(pa);
			List<E> datas = (List<E>) cfg.asObject(p.getPartitionData());
			it_datas = datas.iterator();
			return it_datas.next();
		}
		return null;
	}
}
