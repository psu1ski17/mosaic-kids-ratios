package org.drumm.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Table<T1, T2, T3> {
	private HashMap<T1, Map<T2, T3>> map;

	public Table() {
		clear();
	}

	public void clear() {
		map = new HashMap<T1, Map<T2, T3>>();

	}

	public boolean containsKey(T1 col, T2 row) {
		Map<T2, T3> rows = map.get(col);
		if (rows != null) {
			return rows.containsKey(row);
		}
		return false;
	}

	public boolean containsValue(T3 value) {

		for (Entry<T1, Map<T2, T3>> entry : map.entrySet()) {
			Map<T2, T3> rows = entry.getValue();
			if (rows != null) {
				for (Entry<T2, T3> cell : rows.entrySet()) {
					if (value == null) {
						if (cell.getValue() == null) {

							return true;
						}
					} else {
						if (value.equals(cell.getValue())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// public Set<java.util.Map.Entry<T1, T3>> entrySet() {
	// // Auto-generated method stub
	// return null;
	// }

	public T3 get(T1 col, T2 row) {
		Map<T2, T3> rows = map.get(col);
		if (rows == null) {
			return null;
		} else {
			return rows.get(row);
		}
	}

	public boolean isEmpty() {
		if (map.isEmpty()) {
			return true;
		} else {
			for (Entry<T1, Map<T2, T3>> entry : map.entrySet()) {
				Map<T2, T3> rows = entry.getValue();
				if (rows != null && !rows.isEmpty()) {
					return false;
				}
			}
			return true;
		}
	}

	// @Override
	// public Set<T1> keySet() {
	// // Auto-generated method stub
	// return null;
	// }

	public synchronized T3 put(T1 col, T2 row, T3 value) {
		Map<T2, T3> rows = map.get(col);
		if (rows == null) {
			rows = new HashMap<T2, T3>();
			map.put(col, rows);
		}
		rows.put(row, value);
		return value;
	}

	public synchronized T3 remove(T1 col, T2 row) {
		Map<T2, T3> rows = map.get(col);
		if (rows != null) {
			return rows.remove(row);
		}
		return null;
	}

	public int size() {
		return getSizeSlow();
	}

	private int getSizeSlow() {
		if (map.isEmpty()) {
			return 0;
		} else {
			int size = 0;
			for (Entry<T1, Map<T2, T3>> entry : map.entrySet()) {
				Map<T2, T3> rows = entry.getValue();
				if (rows != null && !rows.isEmpty()) {
					size += rows.size();
				}
			}
			return size;
		}
	}
}
