package districoll.node;

import java.util.HashMap;

import org.jgroups.JChannel;
import org.jgroups.blocks.MessageDispatcher;

import districoll.core.Config;

public class ClientNodeFactory {

	private static HashMap<String, ClientNode> clientNodeMap = new HashMap<String, ClientNode>();

	public static ClientNode getNode(String clusterName) {
		if (!clientNodeMap.containsKey(clusterName)) {
			try {
				JChannel chn = new JChannel();
				ClientNode cn = new ClientNode();
				MessageDispatcher md = new MessageDispatcher(chn, cn);
				md.setMembershipListener(cn);
				cn.setMd(md);
				md.start();
				chn.connect(clusterName);

				while (cn.getServers().isEmpty()) {
					Thread.sleep(1000);
					System.out
							.println("did not find server node in cluster yet");
				}
				System.out.println("find " + cn.getServers().size()
						+ " servers node in cluster");

				clientNodeMap.put(clusterName, cn);
				return cn;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return clientNodeMap.get(clusterName);
		}
	}

	public static ClientNode getDefaultNode() {
		return getNode(Config.getInstance().getClusterName());
	}
}
