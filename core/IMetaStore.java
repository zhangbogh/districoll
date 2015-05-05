package districoll.core;

import java.io.Serializable;
/**
 * ��servernode������
 * @author zhangbo07
 *
 */
public interface IMetaStore extends Serializable {
	public void putMeta(String id, MetaAnchor meta);

	public MetaAnchor getMeta(String id);

	public void removeMeta(String id);
}
