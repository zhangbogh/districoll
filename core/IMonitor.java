package districoll.core;

import org.jgroups.Address;

public interface IMonitor {
	// 分布式对象加锁
	public boolean tryLock(IDistributeObject dio);

	// 解锁
	public void unLock(IDistributeObject dio);

	// 谁掌握着锁
	public Address whoHasLock(IDistributeObject dio);
}
