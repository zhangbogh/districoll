package districoll.core;

import java.io.Externalizable;

/**
 * 数据切割器
 * 
 * @author zhangbo07
 *
 */
public interface ISplit extends Externalizable {
	// 添加对象
	public void addObject(Object e);

	// 需要切割
	public boolean needSplit();

	// 切割成字节流
	public byte[] split();

	// 字节型返回字节大小，数量型返回元素数量
	public int getSplitSize();
}
