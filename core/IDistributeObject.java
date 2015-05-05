package districoll.core;

public interface IDistributeObject {
	// 返回meta信息
	public MetaAnchor getMetaAnchor();

	// 分布式对象的名称
	public String getName();

	// 集群内销毁分布式对象
	public void destory();
}
