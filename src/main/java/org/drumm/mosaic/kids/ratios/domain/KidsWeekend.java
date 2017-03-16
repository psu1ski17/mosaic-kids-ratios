package org.drumm.mosaic.kids.ratios.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class KidsWeekend {
	private String weekend;
	private Map<String, KidsService> services;

	public KidsWeekend(String weekend, Map<String, KidsService> services) {
		super();
		this.weekend = weekend;
		this.services = services;
	}

	public KidsWeekend() {
		this.services = new HashMap<String, KidsService>();
	}

	public String getWeekend() {
		return weekend;
	}

	public void setWeekend(String weekend) {
		this.weekend = weekend;
	}

	public Map<String, KidsService> getServices() {
		return services;
	}

	public void setServices(Map<String, KidsService> services) {
		this.services = services;
	}

	public void addRoom(KidsRoom room) {
		String weekendName = this.getWeekend();
		if (weekendName.equals(room.getWeekend())) {
			KidsService serviceToAdd = this.services.get(room.getService());
			if (serviceToAdd == null) {
				serviceToAdd = new KidsService();
				serviceToAdd.setWeekend(room.getWeekend());
				serviceToAdd.setService(room.getService());
				services.put(room.getService(), serviceToAdd);
			}
			serviceToAdd.addRoom(room);
		} else {
			throw new IllegalArgumentException(
					"Input has more than one weekend.  name1=" + weekendName
							+ " name2=" + room.getWeekend());
		}
	}

	public Collection<KidsRoom> toRooms() {
		Collection<KidsRoom> rooms = new ArrayList<KidsRoom>();
		for (Entry<String, KidsService> entry : services.entrySet()) {
			Collection<KidsRoom> subRooms = entry.getValue().toRooms();
			rooms.addAll(subRooms);
		}
		return rooms;
	}

}
