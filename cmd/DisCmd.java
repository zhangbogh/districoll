package districoll.cmd;

import org.jgroups.Address;

import districoll.core.ISplit;
import districoll.core.MetaAnchor;
import districoll.core.Partition;
import districoll.core.PartitionAnchor;

public class DisCmd implements IDisCmd {
	private static final long serialVersionUID = -7552739016687689779L;
	private CmdType cmdType;
	private Cmd cmd;
	private Address src;

	// req
	public String objName;
	public byte[] datas;
	public String partitionId;
	public ISplit split;

	// rsp
	public MetaAnchor metaAnchor;
	public PartitionAnchor partitionAnchor;
	public Partition partition;

	public DisCmd setPartitionId(String partitionId) {
		this.partitionId = partitionId;
		return this;
	}

	public DisCmd setCmdType(CmdType type) {
		this.cmdType = type;
		return this;
	}

	public DisCmd setCmd(Cmd cmd) {
		this.cmd = cmd;
		return this;
	}

	public IDisCmd setSrc(Address src) {
		this.src = src;
		return this;
	}

	public DisCmd setObjName(String name) {
		this.objName = name;
		return this;
	}

	public DisCmd setDatas(byte[] data) {
		this.datas = data;
		return this;
	}

	public DisCmd setMetaAnchor(MetaAnchor meta) {
		this.metaAnchor = meta;
		return this;
	}

	public DisCmd setPartitionAnchor(PartitionAnchor partition) {
		this.partitionAnchor = partition;
		return this;
	}

	public DisCmd setPartition(Partition partition) {
		this.partition = partition;
		return this;
	}

	public DisCmd setSplit(ISplit split) {
		this.split = split;
		return this;
	}

	@Override
	public CmdType getCmdType() {
		return cmdType;
	}

	@Override
	public Cmd getCmd() {
		return cmd;
	}

	@Override
	public Address getSrc() {
		return src;
	}
}
