package districoll.node;

import org.jgroups.Message;

import districoll.cmd.Cmd;
import districoll.cmd.CmdType;
import districoll.cmd.IDisCmd;

public class ClientNode extends Node {
	@Override
	public Object handle(Message msg) throws Exception {
		IDisCmd cmd = (IDisCmd) msg.getObject();
		switch (cmd.getCmdType()) {
		// 成员管理
		case MEMBER_OP:
			return memberOp(cmd);
		default:
			return null;
		}
	}

	private IDisCmd memberOp(IDisCmd cmd) {
		switch (cmd.getCmd()) {
		case REQ_NODETYPE:
			return newDisCmd().setCmdType(CmdType.MEMBER_OP).setCmd(
					Cmd.RSP_CLIENTNODE);
		default:
			return null;
		}
	}
}
