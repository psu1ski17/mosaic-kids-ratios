package org.drumm.mosaic.kids.ratios.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.drumm.mosaic.kids.ratios.domain.KidsRoom;
import org.drumm.mosaic.kids.ratios.domain.KidsService;
import org.drumm.mosaic.kids.ratios.domain.KidsWeekend;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestResource {

	private static final String TEST_WEEKEND = "Test-Weekend";
	private static final String TEST_CAMPUS = "Test-Campus";
	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
	private long now = System.currentTimeMillis();

	@GET
	@Path("/weekend")
	public KidsWeekend getWeekend() {
		Map<String, KidsService> services = new TreeMap<String, KidsService>();
		services.put("Sun-9:00", getService("Sun-9:00"));
		services.put("Sat-6:00", getService("Sat-6:00"));
		services.put("Arundel-Saturday", getService("Arundel-Saturday"));
		KidsWeekend weekend = new KidsWeekend(TEST_WEEKEND, services);
		return weekend;
	}

	@GET
	@Path("/service")
	public KidsService getService() {
		return getService("Sun-9:00");
	}

	@GET
	@Path("rooms")
	public Collection<KidsRoom> getRooms() {

		ArrayList<KidsRoom> rooms = new ArrayList<KidsRoom>();
		rooms.add(new KidsRoom("Upstreet", "Sun-9:00", TEST_WEEKEND, 3, 2,
				TEST_CAMPUS, now - MILLIS_PER_DAY));
		rooms.add(new KidsRoom("Room1", "Sun-9:00", TEST_WEEKEND, 4, 2,
				TEST_CAMPUS, now - MILLIS_PER_DAY * 2));
		rooms.add(new KidsRoom("Upstreet", "Sat-6:00", TEST_WEEKEND, 6, 3,
				TEST_CAMPUS, now - MILLIS_PER_DAY * 3));
		rooms.add(new KidsRoom("Room1", "Sat-6:00", TEST_WEEKEND, 8, 4,
				TEST_CAMPUS, now - MILLIS_PER_DAY * 4));
		rooms.add(new KidsRoom("Upstreet", "Arundel-Sat", TEST_WEEKEND, 10, 5,
				TEST_CAMPUS, now - MILLIS_PER_DAY * 5));
		rooms.add(new KidsRoom("Room1", "Arundel-Sat", TEST_WEEKEND, 12, 6,
				TEST_CAMPUS, now - MILLIS_PER_DAY * 6));
		return rooms;
	}

	@POST
	@Path("rooms")
	public Collection<KidsRoom> postRooms(Collection<KidsRoom> rooms) {
		if (rooms != null && rooms.size() > 0) {
			return rooms;
		} else {
			throw new WebApplicationException(500);
		}

	}

	@GET
	@Path("room")
	public KidsRoom getRoom() {
		return new KidsRoom("Upstreet", "Sun-9:00", TEST_WEEKEND, 5, 2,
				TEST_CAMPUS, now - MILLIS_PER_DAY * 2);
	}

	private KidsService getService(String serviceTitle) {
		Map<String, KidsRoom> rooms = new TreeMap<String, KidsRoom>();
		rooms.put(getRoom().getRoom(), getRoom());
		rooms.put("Room1", new KidsRoom("Room1", serviceTitle, TEST_WEEKEND, 3,
				2, TEST_CAMPUS, now - MILLIS_PER_DAY * 2));
		KidsService service = new KidsService(serviceTitle, TEST_WEEKEND, rooms);
		return service;
	}

	@POST
	@Path("/room")
	public KidsRoom postRoom(KidsRoom room) {
		if (room != null) {
			return room;
		} else {
			throw new WebApplicationException(500);
		}
	}

	@POST
	@Path("/service")
	public KidsService postRoom(KidsService service) {
		if (service != null && service.getRooms().size() > 0) {
			return service;
		} else {
			throw new WebApplicationException(500);
		}
	}

	@POST
	@Path("/weekend")
	public KidsWeekend postRoom(KidsWeekend weekend) {
		if (weekend != null) {
			return weekend;
		} else {
			throw new WebApplicationException(500);
		}
	}
}
