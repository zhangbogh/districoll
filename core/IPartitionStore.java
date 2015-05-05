package districoll.core;

public interface IPartitionStore {
	public void putPartition(String id, Partition part);

	public Partition getPartition(String id);

	public void removePartition(String id);
}
