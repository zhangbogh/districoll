package districoll.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgroups.Address;

import districoll.exception.NearNotFindException;

public class RouteUtil {
	public static Address nearestAddress(String id, List<Address> servers) {
		int code1 = id.hashCode();
		int[] codes = toHashCodes(servers);
		return servers.get(nearestIdx(code1, codes));
	}

	public static int[] toHashCodes(List<?> l) {
		int[] result = new int[l.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = l.get(i).hashCode();
		}
		return result;
	}

	// 返回最接近a值得b数组的索引
	public static int nearestIdx(int a, int[] as) {
		int[] k = nearNIdx(a, as, 1);
		return k[0];
	}

	/**
	 * 返回最近的n个值得位置
	 * 
	 * @param a
	 * @param as
	 * @param n
	 * @return
	 */
	public static int[] nearNIdx(int a, int[] as, int n) {
		if (n > as.length) {
			throw new NearNotFindException();
		}
		ArrayList<Pair> l = new ArrayList<Pair>(as.length);
		for (int i = 0; i < as.length; i++) {
			Pair p = new Pair();
			p.value = Math.abs(as[i] - a);
			p.idx = i;
			l.add(p);
		}

		Collections.sort(l);

		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = l.get(i).idx;
		}
		return result;
	}

	private static class Pair implements Comparable<Pair> {
		int value;
		int idx;

		@Override
		public int compareTo(Pair o) {
			return this.value - o.value;
		}
	}

}
