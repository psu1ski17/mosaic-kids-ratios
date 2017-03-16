package org.drumm.mosaic.kids.ratios.domain;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Not Thread Safe!
 *
 */
public class WeekendRatios {
	private Map<String, Map<String, RoomRatio>> serviceToRoomToRatio;

	public WeekendRatios() {
		this.serviceToRoomToRatio = new TreeMap<String, Map<String, RoomRatio>>();
	}

	public Map<String, Map<String, RoomRatio>> getServiceToRoomToRatio() {
		return serviceToRoomToRatio;
	}

	@JsonValue
	public void setServiceToRoomToRatio(
			Map<String, Map<String, RoomRatio>> serviceToRoomToRatio) {
		this.serviceToRoomToRatio = serviceToRoomToRatio;
	}

	public RoomRatio put(String service, String room, RoomRatio value) {
		Map<String, RoomRatio> rows = serviceToRoomToRatio.get(service);
		if (rows == null) {
			rows = new TreeMap<String, RoomRatio>();
			serviceToRoomToRatio.put(service, rows);
		}
		rows.put(room, value);
		return value;
	}

	@JsonIgnore
	public RoomRatio get(String service, String room) {
		Map<String, RoomRatio> rows = serviceToRoomToRatio.get(service);
		if (rows == null) {
			return null;
		} else {
			return rows.get(room);
		}
	}

	@JsonIgnore
	public Map<String, Map<String, RoomRatio>> getMap() {
		return serviceToRoomToRatio;
	}

	public void put(RoomRatio ratio) {
		put(ratio.getService(),ratio.getRoom(), ratio);		
	}
}
