package districoll.cmd;

import java.io.Serializable;

import org.jgroups.Address;

public interface IDisCmd extends Serializable {
	// 信令类型
	public CmdType getCmdType();

	// 信令ID
	public Cmd getCmd();

	// 信令产生源
	public Address getSrc();
}
