package org.drumm.mosaic.kids.ratios.store;

import java.util.Collection;

import org.drumm.mosaic.kids.ratios.domain.KidsRoom;

public interface IKidsRoomStore {

	void putKidsRoom(KidsRoom kidsRoom);

	void putKidsRooms(Collection<KidsRoom> kidsRooms);

	Collection<KidsRoom> getKidsRooms(long startTime, long endTime);

}
