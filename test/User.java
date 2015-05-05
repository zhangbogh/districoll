package districoll.test;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -1261879202317603517L;
	int age;
	String name;
	byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
