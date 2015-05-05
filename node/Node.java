package districoll.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgroups.Address;
import org.jgroups.MembershipListener;
import org.jgroups.Message;
import org.jgroups.View;
import org.jgroups.blocks.MessageDispatcher;
import org.jgroups.blocks.RequestHandler;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.ResponseMode;
import org.jgroups.util.Rsp;
import org.jgroups.util.RspList;

import districoll.cmd.Cmd;
import districoll.cmd.CmdType;
import districoll.cmd.DisCmd;
import districoll.cmd.IDisCmd;
import districoll.core.Config;

public abstract class Node implements RequestHandler, MembershipListener {
	protected static RequestOptions ro_none = new RequestOptions(
			ResponseMode.GET_NONE, Config.getInstance().getTimeOutSeconds());
	protected static RequestOptions ro_all = new RequestOptions(
			ResponseMode.GET_ALL, Config.getInstance().getTimeOutSeconds());
	protected static RequestOptions ro_first = new RequestOptions(
			ResponseMode.GET_FIRST, Config.getInstance().getTimeOutSeconds());
	protected MessageDispatcher md;
	protected List<Address> servers = new ArrayList<Address>();

	public void setMd(MessageDispatcher md) {
		this.md = md;
	}

	public void asyncSendCmd(Address address, IDisCmd cmd) {
		try {
			md.sendMessage(new Message(address, cmd), ro_none);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IDisCmd syncSendCmd(Address address, IDisCmd cmd) {
		try {
			return md.sendMessage(new Message(address, cmd), ro_first);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Object> syncCastSendCmd(List<Address> dests, IDisCmd cmd) {
		try {
			return md.castMessage(dests, new Message(null, cmd), ro_all)
					.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void viewAccepted(View new_view) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<Address> all = new_view.getMembers();
				RspList<IDisCmd> allRsp;
				try {
					allRsp = md
							.castMessage(
									all,
									new Message(null, new DisCmd().setCmdType(
											CmdType.MEMBER_OP).setCmd(
											Cmd.REQ_NODETYPE)), ro_all);
					servers.clear();
					for (Rsp<IDisCmd> a : allRsp) {
						if (a.wasReceived()
								&& a.getValue().getCmd() == Cmd.RSP_SERVERNODE) {
							servers.add(a.getSender());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// server需要排序，为了定位meta信息用
				Collections.sort(servers);
				System.out.println("server size:" + servers.size());
			}
		}).start();
	}

	@Override
	public void suspect(Address suspected_mbr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void block() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unblock() {
		// TODO Auto-generated method stub

	}

	@Override
	public abstract Object handle(Message msg) throws Exception;

	public List<Address> getServers() {
		return servers;
	}

	public DisCmd newDisCmd() {
		DisCmd dc = new DisCmd();
		dc.setSrc(md.getChannel().getAddress());
		return dc;
	}

	public DisCmd newRspOkCmd() {
		DisCmd dc = new DisCmd();
		dc.setSrc(md.getChannel().getAddress());
		dc.setCmd(Cmd.RSP_OK);
		return dc;
	}

	public DisCmd newRspFailCmd() {
		DisCmd dc = new DisCmd();
		dc.setSrc(md.getChannel().getAddress());
		dc.setCmd(Cmd.RSP_FAIL);
		return dc;
	}

}
