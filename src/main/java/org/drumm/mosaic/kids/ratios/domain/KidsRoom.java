package org.drumm.mosaic.kids.ratios.domain;

public class KidsRoom {
	public static final String TIME = "time";
	public static final String SERVICE = "service";
	public static final String WEEKEND = "weekend";
	public static final String NUM_KIDS = "numKids";
	public static final String NUM_VOLUNTEERS = "numVolunteers";
	public static final String CAMPUS = "campus";
	public static final String ROOM = "room";
	
	private String room;
	private String service;
	private String weekend;
	private int numKids;
	private int numVolunteers;
	private String campus;
	private long time;

	public KidsRoom(String room, String service, String weekend, int numKids,
			int numVolunteers, String campus, long time) {
		super();
		this.room = room;
		this.service = service;
		this.weekend = weekend;
		this.numKids = numKids;
		this.numVolunteers = numVolunteers;
		this.campus = campus;
		this.time=time;
	}
	
	public KidsRoom(){
		
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
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

	public int getNumKids() {
		return numKids;
	}

	public void setNumKids(int numKids) {
		this.numKids = numKids;
	}

	public int getNumVolunteers() {
		return numVolunteers;
	}

	public void setNumVolunteers(int numVolunteers) {
		this.numVolunteers = numVolunteers;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "KidsRoom [room=" + room + ", service=" + service + ", weekend="
				+ weekend + ", numKids=" + numKids + ", numVolunteers="
				+ numVolunteers + ", campus=" + campus + ", time=" + time + "]";
	}

}
