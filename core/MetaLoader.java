package districoll.core;

import java.util.List;

import org.jgroups.Address;

import districoll.cmd.Cmd;
import districoll.cmd.CmdType;
import districoll.cmd.DisCmd;
import districoll.cmd.IDisCmd;
import districoll.node.ClientNode;
import districoll.node.ClientNodeFactory;
import districoll.util.RouteUtil;

public class MetaLoader<E> {
	Address holder;
	ClientNode cn;
	String objName;
	ISplit split;

	public MetaLoader(String objName) {
		this.cn = ClientNodeFactory.getDefaultNode();
		this.objName = objName;
		// ȫ��ѯ���Ƿ��Ѿ����˸ö�������Ѿ����ˣ�ʹ��ԭ���еĶ���
		List<Object> rsps = cn.syncCastSendCmd(cn.getServers(), cn.newDisCmd()
				.setCmdType(CmdType.DATA_META).setCmd(Cmd.REQ_META_GET)
				.setObjName(objName));
		boolean hasMeta = false;
		for (Object o : rsps) {
			DisCmd cmd = (DisCmd) o;
			if (cmd.getCmd() == Cmd.RSP_OK) {
				hasMeta = true;
				this.holder = cmd.getSrc();
				this.split = cmd.metaAnchor.getSplit();
				break;
			}
		}
		// ��һ�δ���
		if (!hasMeta) {
			this.holder = RouteUtil.nearestAddress(objName, cn.getServers());
			this.split = Config.getInstance().getDisListSplit();
			// ˢ�½���������
			addMeta();
		}
	}

	public void addMeta() {
		cn.syncSendCmd(holder, new DisCmd().setCmdType(CmdType.DATA_META)
				.setCmd(Cmd.REQ_META_ADD).setObjName(objName).setSplit(split));
	}

	public MetaAnchor getMeta() {
		IDisCmd rsp = cn.syncSendCmd(
				holder,
				new DisCmd().setCmdType(CmdType.DATA_META)
						.setCmd(Cmd.REQ_META_GET).setObjName(objName));
		DisCmd cmd = (DisCmd) rsp;
		return cmd.metaAnchor;
	}

	public void delMeta() {
		cn.syncSendCmd(holder, new DisCmd().setCmdType(CmdType.DATA_META)
				.setCmd(Cmd.REQ_META_DEL).setObjName(objName));
	}

	public PartitionAnchor newPartition() {
		IDisCmd rsp = cn.syncSendCmd(
				holder,
				new DisCmd().setCmdType(CmdType.DATA_META)
						.setCmd(Cmd.REQ_META_NEWPARTITION).setObjName(objName));
		DisCmd cmd = (DisCmd) rsp;
		return cmd.partitionAnchor;
	}
}
