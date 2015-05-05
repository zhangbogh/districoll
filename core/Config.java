package districoll.core;

public class Config {
	private static Config conf;

	private String clusterName = "testCluster";

	private Config() {

	}

	public int getBlockSize() {
		return 1024 * 1024;
	}

	public String getClusterName() {
		return clusterName;
	}

	public static Config getInstance() {
		if (conf == null) {
			conf = new Config();
		}
		return conf;
	}

	public int getTimeOutSeconds() {
		return 30 * 1000;
	}

	public ISplit getDisListSplit() {
		return new LengthSplit(10000);
	}
}
