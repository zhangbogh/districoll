package districoll.util;

import java.util.UUID;

public class IdUtil {
	public static String getPartitionId() {
		return UUID.randomUUID().toString();
	}
}
