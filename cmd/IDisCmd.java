package districoll.cmd;

import java.io.Serializable;

import org.jgroups.Address;

public interface IDisCmd extends Serializable {
	// ��������
	public CmdType getCmdType();

	// ����ID
	public Cmd getCmd();

	// �������Դ
	public Address getSrc();
}
