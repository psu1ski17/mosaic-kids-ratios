package org.drumm.mosaic.kids.ratios.store;

import org.drumm.mosaic.kids.ratios.domain.RoomRatio;
import org.drumm.mosaic.kids.ratios.domain.WeekendRatios;

public interface IKidsRatioStore {

	public WeekendRatios getLatestRatio();
	public WeekendRatios getRatioPriorToDate(long priorToDateMillis);
	public void addRatios(WeekendRatios ratios);
	public void addRatio(RoomRatio ratio);
}
