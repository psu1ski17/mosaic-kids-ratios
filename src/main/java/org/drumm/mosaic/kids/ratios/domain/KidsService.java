package org.drumm.mosaic.kids.ratios.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KidsService {

	private String service;
	private String weekend;
	private Map<String, KidsRoom> rooms;

	public KidsService(String service, String weekend,
			Map<String, KidsRoom> rooms) {
		super();
		this.service = service;
		this.weekend = weekend;
		this.rooms = rooms;
	}

	public KidsService() {
		this.rooms = new HashMap<String, KidsRoom>();
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getWeekend() {
		return weekend;
	}

	public void setWeekend(String weekend) {
		this.weekend = weekend;
	}

	public Map<String, KidsRoom> getRooms() {
		return rooms;
	}

	public void setRooms(Map<String, KidsRoom> rooms) {
		this.rooms = rooms;
	}

	public void addRoom(KidsRoom room) {
		// TODO verify no repeats?
		if (room.getService().equals(service)
				&& room.getWeekend().equals(weekend)) {
			rooms.put(room.getRoom(), room);
		} else {
			throw new IllegalArgumentException(
					"Unable to add room to service.  Room=" + room
							+ " serviceName=" + this.service + " weekendName="
							+ this.weekend);
		}
	}

	public Collection<KidsRoom> toRooms() {
		return rooms.values();
	}
}
