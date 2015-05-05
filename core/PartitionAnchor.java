package districoll.core;

import java.io.Serializable;
import java.util.List;

import org.jgroups.Address;

public class PartitionAnchor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3607559477361945441L;
	String partitionId;
	Address holdServer;
	List<Address> backupServers;

	public String getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(String partitionId) {
		this.partitionId = partitionId;
	}

	public Address getHoldServer() {
		return holdServer;
	}

	public void setHoldServer(Address holdServer) {
		this.holdServer = holdServer;
	}

	public List<Address> getBackupServers() {
		return backupServers;
	}

	public void setBackupServers(List<Address> backupServers) {
		this.backupServers = backupServers;
	}
}
