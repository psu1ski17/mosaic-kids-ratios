package org.drumm.mosaic.kids.ratios.store.memory;

import org.drumm.mosaic.kids.ratios.domain.RoomRatio;
import org.drumm.mosaic.kids.ratios.domain.WeekendRatios;
import org.drumm.mosaic.kids.ratios.store.IKidsRatioStore;

public class MemoryKidsRatioStore implements IKidsRatioStore {
	

	private WeekendRatios ratios;

	public MemoryKidsRatioStore() {
		ratios = new WeekendRatios();
		for (String service: MemoryDataSet.SERVICES){
			int index=0;
			for (String room: MemoryDataSet.ROOMS){
				RoomRatio ratio = new RoomRatio();
				ratio.setDateSet(1);
				ratio.setKids(MemoryDataSet.RATIOS[index]);
				ratio.setVolunteers(1);
				ratio.setService(service);
				ratio.setRoom(room);
				ratios.put(ratio);
				index++;
			}
		}
	}

	@Override
	public WeekendRatios getLatestRatio() {
		return ratios;
	}

	@Override
	public WeekendRatios getRatioPriorToDate(long priorToDateMillis) {
		return ratios;
	}

	@Override
	public void addRatios(WeekendRatios ratios) {
		this.ratios=ratios;
	}

	@Override
	public void addRatio(RoomRatio ratio) {
		this.ratios.put(ratio);
	}
}
