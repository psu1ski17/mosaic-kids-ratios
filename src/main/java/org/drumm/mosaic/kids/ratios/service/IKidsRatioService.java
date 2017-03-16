package org.drumm.mosaic.kids.ratios.service;

import java.util.Collection;
import java.util.Map;

import org.drumm.mosaic.kids.ratios.domain.KidsRoom;
import org.drumm.mosaic.kids.ratios.domain.KidsService;
import org.drumm.mosaic.kids.ratios.domain.KidsWeekend;
import org.drumm.mosaic.kids.ratios.domain.WeekendRatios;

public interface IKidsRatioService {

	void addWeekend(KidsWeekend weekend);

	void addService(KidsService service);

	void addRoom(KidsRoom room);

	Map<String, KidsWeekend> getWeekends(long startDate, long endDate);

	Map<String, KidsService> getServices(long startDate, long endDate);

	Collection<KidsRoom> getRooms(long startDate, long endDate);

	WeekendRatios getRatios(long maxDateMillis);

}
