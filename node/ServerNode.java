package districoll.node;

import java.util.ArrayList;
import java.util.List;

import org.jgroups.Address;
import org.jgroups.Message;

import districoll.cmd.Cmd;
import districoll.cmd.CmdType;
import districoll.cmd.DisCmd;
import districoll.cmd.IDisCmd;
import districoll.core.HeapMetaStore;
import districoll.core.HeapPartitionStore;
import districoll.core.IMetaStore;
import districoll.core.IPartitionStore;
import districoll.core.MetaAnchor;
import districoll.core.Partition;
import districoll.core.PartitionAnchor;
import districoll.util.IdUtil;
import districoll.util.RouteUtil;

public class ServerNode extends Node {
	// localpartition
	private IPartitionStore partitionStore = new HeapPartitionStore();
	// share the meta between the servers
	private IMetaStore metaStore = new HeapMetaStore();

	public static void main(String[] args) throws InterruptedException {
		ServerNodeFactory.getDefaultNode();
		while (true) {
			Thread.sleep(2000);
		}
	}

	@Override
	public IDisCmd handle(Message msg) throws Exception {
		IDisCmd cmd = (IDisCmd) msg.getObject();
		switch (cmd.getCmdType()) {
		// 成员管理
		case MEMBER_OP:
			return memberOp(cmd);
			// 数据管理
		case DATA_OP:
			return dataOp(cmd);
			// 数据管理
		case DATA_META:
			return dataMeta(cmd);

		default:
			return null;
		}
	}

	private IDisCmd memberOp(IDisCmd cmd) {
		switch (cmd.getCmd()) {
		case REQ_NODETYPE:
			return newDisCmd().setCmdType(CmdType.MEMBER_OP).setCmd(
					Cmd.RSP_SERVERNODE);
		default:
			return null;
		}
	}

	private IDisCmd dataOp(IDisCmd cmd) {
		switch (cmd.getCmd()) {
		case REQ_PARTITION_REFRESH:
			return partitionRefresh(cmd);
		case REQ_PARTITION_GET:
			return partitionGet(cmd);
		case REQ_PARTITION_DEL:
			return partitionDel(cmd);
		default:
			return null;
		}
	}

	private IDisCmd partitionDel(IDisCmd cmd) {
		DisCmd dc = (DisCmd) cmd;
		if (dc.partitionId != null) {
			partitionStore.removePartition(dc.partitionId);
			return newRspOkCmd().setCmdType(CmdType.DATA_OP);
		}
		return newRspFailCmd().setCmdType(CmdType.DATA_OP);
	}

	private IDisCmd partitionGet(IDisCmd cmd) {
		DisCmd dc = (DisCmd) cmd;
		if (dc.partitionId != null) {
			return newRspOkCmd().setPartition(
					partitionStore.getPartition(dc.partitionId)).setCmdType(
					CmdType.DATA_OP);
		}
		return newRspFailCmd().setCmdType(CmdType.DATA_OP);
	}

	private IDisCmd partitionRefresh(IDisCmd cmd) {
		DisCmd dc = (DisCmd) cmd;
		if (dc.partitionId != null) {
			partitionStore.putPartition(dc.partitionId, new Partition(
					dc.partitionId, dc.datas));
		}
		return newRspOkCmd().setCmdType(CmdType.DATA_OP);
	}

	private IDisCmd dataMeta(IDisCmd cmd) {
		switch (cmd.getCmd()) {
		case REQ_META_ADD:
			return addMeta(cmd);
		case REQ_META_GET:
			return getMeta(cmd);
		case REQ_META_DEL:
			return delMeta(cmd);
		case REQ_META_NEWPARTITION:
			return newPartition(cmd);
		default:
			return null;
		}
	}

	private IDisCmd delMeta(IDisCmd cmd) {
		DisCmd req = (DisCmd) cmd;
		if (metaStore.getMeta(req.objName) != null) {
			MetaAnchor ma = metaStore.getMeta(req.objName);
			List<PartitionAnchor> lp = ma.getPartAnchors();
			for (PartitionAnchor pa : lp) {
				syncSendCmd(
						pa.getHoldServer(),
						newDisCmd().setCmdType(CmdType.DATA_OP)
								.setCmd(Cmd.REQ_PARTITION_DEL)
								.setPartitionAnchor(pa));
			}
			metaStore.removeMeta(req.objName);
			return newRspOkCmd().setCmdType(CmdType.DATA_META);
		}
		return newRspFailCmd().setCmdType(CmdType.DATA_META);
	}

	private IDisCmd newPartition(IDisCmd cmd) {
		DisCmd dc = (DisCmd) cmd;
		PartitionAnchor pa = new PartitionAnchor();
		MetaAnchor ma = metaStore.getMeta(dc.objName);
		if (ma != null) {
			String id = IdUtil.getPartitionId();
			Address holder = RouteUtil.nearestAddress(id, servers);
			pa.setPartitionId(id);
			pa.setHoldServer(holder);

			ma.getPartAnchors().add(pa);
			ma.setSumSplitSize(ma.getSumSplitSize()
					+ ma.getSplit().getSplitSize());
		}
		return newRspOkCmd().setCmdType(CmdType.DATA_META).setPartitionAnchor(
				pa);
	}

	private IDisCmd addMeta(IDisCmd cmd) {
		DisCmd req = (DisCmd) cmd;
		MetaAnchor meta = new MetaAnchor();
		meta.setDisObjName(req.objName);
		meta.setSplit(req.split);
		meta.setPartAnchors(new ArrayList<PartitionAnchor>());
		metaStore.putMeta(req.objName, meta);
		return newRspOkCmd().setCmdType(CmdType.DATA_META);
	}

	private IDisCmd getMeta(IDisCmd cmd) {
		DisCmd req = (DisCmd) cmd;
		if (metaStore.getMeta(req.objName) != null) {
			return newRspOkCmd().setMetaAnchor(metaStore.getMeta(req.objName))
					.setCmdType(CmdType.DATA_META);
		} else {
			return newRspFailCmd().setCmdType(CmdType.DATA_META);
		}
	}
}
