package districoll.core;

import java.io.Serializable;

public class Partition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6573376079516836356L;
	String partitionId;
	byte[] datas;

	public Partition(String id, byte[] data) {
		this.partitionId = id;
		this.datas = data;
	}

	public String getPartitionId() {
		return partitionId;
	}

	public byte[] getPartitionData() {
		return datas;
	}

}
