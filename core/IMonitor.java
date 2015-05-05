package districoll.core;

import org.jgroups.Address;

public interface IMonitor {
	// �ֲ�ʽ�������
	public boolean tryLock(IDistributeObject dio);

	// ����
	public void unLock(IDistributeObject dio);

	// ˭��������
	public Address whoHasLock(IDistributeObject dio);
}
