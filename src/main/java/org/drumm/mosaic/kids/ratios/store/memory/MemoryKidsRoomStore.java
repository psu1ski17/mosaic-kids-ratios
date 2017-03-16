package org.drumm.mosaic.kids.ratios.store.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.drumm.mosaic.kids.ratios.domain.KidsRoom;
import org.drumm.mosaic.kids.ratios.store.IKidsRoomStore;

public class MemoryKidsRoomStore implements IKidsRoomStore {

	private List<KidsRoom> rooms;

	public MemoryKidsRoomStore(){
		rooms = new LinkedList<KidsRoom>();
		int sIdx=0;
		int rIdx;
		for (String service:MemoryDataSet.SERVICES){
			rIdx=0;
			for (String room: MemoryDataSet.ROOMS){
				KidsRoom kidsRoom = new KidsRoom();
				kidsRoom.setNumVolunteers(MemoryDataSet.DEFAULT_VOLUNTEERS[sIdx][rIdx]);
				kidsRoom.setNumKids(MemoryDataSet.KIDS[sIdx][rIdx]);
				kidsRoom.setCampus(MemoryDataSet.CAMPUS[sIdx]);
				kidsRoom.setRoom(room);
				kidsRoom.setService(service);
				kidsRoom.setWeekend("MEMORY_WEEKEND");
				rIdx++;
			}
			sIdx++;
		}
	}

	@Override
	public void putKidsRoom(KidsRoom kidsRoom) {
		rooms.add(kidsRoom);
	}

	@Override
	public void putKidsRooms(Collection<KidsRoom> kidsRooms) {
		rooms.addAll(kidsRooms);
	}

	@Override
	public Collection<KidsRoom> getKidsRooms(long startTime, long endTime) {
		List<KidsRoom> subset = new ArrayList<KidsRoom>();
		for (KidsRoom room : rooms) {
			long time = room.getTime();
			if (time >= startTime && time <= endTime) {
				subset.add(room);
			}
		}
		return subset;
	}

}
