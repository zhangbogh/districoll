package districoll.core;

import districoll.cmd.Cmd;
import districoll.cmd.CmdType;
import districoll.cmd.DisCmd;
import districoll.cmd.IDisCmd;
import districoll.node.ClientNode;
import districoll.node.ClientNodeFactory;

public class PartitionLoader {
	ClientNode cn;

	public PartitionLoader() {
		cn = ClientNodeFactory.getDefaultNode();
	}

	public void refreshPartitionData(PartitionAnchor pa, byte[] data) {
		cn.syncSendCmd(
				pa.getHoldServer(),
				cn.newDisCmd().setCmdType(CmdType.DATA_OP)
						.setCmd(Cmd.REQ_PARTITION_REFRESH).setDatas(data)
						.setPartitionId(pa.partitionId));
	}

	public Partition loadPartitionData(PartitionAnchor pa) {
		IDisCmd cmd = cn.syncSendCmd(pa.getHoldServer(), cn.newDisCmd()
				.setCmdType(CmdType.DATA_OP).setCmd(Cmd.REQ_PARTITION_GET)
				.setPartitionId(pa.partitionId));
		DisCmd c = (DisCmd) cmd;
		return c.partition;
	}
}
