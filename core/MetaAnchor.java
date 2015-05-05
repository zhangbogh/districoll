package districoll.core;

import java.io.Serializable;
import java.util.List;

public class MetaAnchor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String disObjName;

	List<PartitionAnchor> partAnchors;

	ISplit split;

	long sumSplitSize;

	public String getDisObjName() {
		return disObjName;
	}

	public void setDisObjName(String disObjName) {
		this.disObjName = disObjName;
	}

	public List<PartitionAnchor> getPartAnchors() {
		return partAnchors;
	}

	public void setPartAnchors(List<PartitionAnchor> partAnchors) {
		this.partAnchors = partAnchors;
	}

	public ISplit getSplit() {
		return split;
	}

	public void setSplit(ISplit split) {
		this.split = split;
	}

	public long getSumSplitSize() {
		return sumSplitSize;
	}

	public void setSumSplitSize(long sumSplitSize) {
		this.sumSplitSize = sumSplitSize;
	}
}
