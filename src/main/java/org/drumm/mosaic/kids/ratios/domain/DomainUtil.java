package org.drumm.mosaic.kids.ratios.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DomainUtil {
	public static KidsWeekend convertToWeekend(Iterable<KidsRoom> iterable) {
		Iterator<KidsRoom> itr = iterable.iterator();
		return convertToWeekend(itr);
	}

	public static KidsWeekend convertToWeekend(Iterator<KidsRoom> itr) {
		KidsWeekend weekend = new KidsWeekend();
		String weekendName = null;
		while (itr.hasNext()) {
			KidsRoom room = itr.next();
			if (weekendName == null) {
				weekendName = room.getWeekend();
				weekend.setWeekend(weekendName);
			}
			weekend.addRoom(room);
		}
		return weekend;
	}

	public static Map<String, KidsWeekend> convertToMultipleWeekends(
			Iterator<KidsRoom> itr) {
		Map<String, KidsWeekend> col = new HashMap<String, KidsWeekend>();
		while (itr.hasNext()) {
			KidsRoom room = itr.next();
			KidsWeekend weekend;
			if (col.containsKey(room.getWeekend())) {
				weekend = col.get(room.getWeekend());
			} else {
				weekend = new KidsWeekend();
				weekend.setWeekend(room.getWeekend());
				col.put(room.getWeekend(), weekend);
			}
			weekend.addRoom(room);
		}
		return col;
	}

	public static Map<String, KidsWeekend> convertToMultipleWeekends(
			Iterable<KidsRoom> rooms) {
		return convertToMultipleWeekends(rooms.iterator());
	}

	public static Map<String, KidsService> convertToMultipleServices(
			Iterable<KidsRoom> rooms) {
		return convertToMultipleServices(rooms.iterator());
	}

	private static Map<String, KidsService> convertToMultipleServices(
			Iterator<KidsRoom> itr) {
		Map<String, KidsService> col = new HashMap<String, KidsService>();
		while (itr.hasNext()) {
			KidsRoom room = itr.next();
			String key=room.getWeekend()+"-"+room.getService();
			KidsService service;
			if (col.containsKey(key)){
				service=col.get(key);
			}else{
				service = new KidsService();
				service.setService(room.getService());
				service.setWeekend(room.getWeekend());
				col.put(key, service);
			}
			service.addRoom(room);
		}
		return col;
	}
}
