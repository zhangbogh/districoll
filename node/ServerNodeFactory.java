package districoll.node;

import java.util.HashMap;

import org.jgroups.JChannel;
import org.jgroups.blocks.MessageDispatcher;

import districoll.core.Config;

public class ServerNodeFactory {
	private static HashMap<String, ServerNode> serverNodeMap = new HashMap<String, ServerNode>();

	public static ServerNode getNode(String clusterName) {
		if (!serverNodeMap.containsKey(clusterName)) {
			try {
				JChannel chn = new JChannel();
				ServerNode sn = new ServerNode();
				MessageDispatcher md = new MessageDispatcher(chn, sn);
				md.setMembershipListener(sn);
				sn.setMd(md);
				md.start();
				chn.connect(clusterName);
				chn.getState(null, 10000);
				serverNodeMap.put(clusterName, sn);
				return sn;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return serverNodeMap.get(clusterName);
		}
	}

	public static ServerNode getDefaultNode() {
		return getNode(Config.getInstance().getClusterName());
	}
}
