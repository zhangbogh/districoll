package districoll.core;

import java.io.Externalizable;

/**
 * �����и���
 * 
 * @author zhangbo07
 *
 */
public interface ISplit extends Externalizable {
	// ��Ӷ���
	public void addObject(Object e);

	// ��Ҫ�и�
	public boolean needSplit();

	// �и���ֽ���
	public byte[] split();

	// �ֽ��ͷ����ֽڴ�С�������ͷ���Ԫ������
	public int getSplitSize();
}
