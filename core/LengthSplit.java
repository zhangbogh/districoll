package districoll.core;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import org.nustaq.serialization.FSTConfiguration;

public class LengthSplit implements ISplit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8908474894410524059L;
	int splitLength;
	List<Object> al = new ArrayList<Object>();
	FSTConfiguration cfg = FSTConfiguration.getDefaultConfiguration();

	public LengthSplit() {
	}

	public LengthSplit(int length) {
		this.splitLength = length;
	}

	@Override
	public void addObject(Object e) {
		al.add(e);
	}

	@Override
	public boolean needSplit() {
		if (al.size() == splitLength) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public byte[] split() {
		byte[] result = cfg.asByteArray(al);
		al.clear();
		return result;
	}

	public int getSplitSize() {
		return splitLength;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(splitLength);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		splitLength = in.readInt();
	}
}
