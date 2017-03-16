package org.drumm.mosaic.kids.ratios.domain;

public class RoomRatio {
	public static final String DATE = "date";
	public static final String KIDS = "kids";
	public static final String VOLUNTEERS = "volunteers";
	public static final String SERVICE = "service";
	public static final String ROOM = "room";
	private int kids;
	private int volunteers;
	private long dateSet;
	private String service;
	private String room;

	public RoomRatio() {

	}

	public RoomRatio(int kids, int volunteers, long dateSet) {
		super();
		this.kids = kids;
		this.volunteers = volunteers;
		this.dateSet = dateSet;
	}

	public int getKids() {
		return kids;
	}

	public void setKids(int kids) {
		this.kids = kids;
	}

	public int getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(int volunteers) {
		this.volunteers = volunteers;
	}

	public long getDateSet() {
		return dateSet;
	}

	public void setDateSet(long dateSet) {
		this.dateSet = dateSet;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
}
