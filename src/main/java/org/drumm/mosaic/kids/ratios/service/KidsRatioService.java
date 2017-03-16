package org.drumm.mosaic.kids.ratios.service;

import java.util.Collection;
import java.util.Map;

import org.drumm.mosaic.kids.ratios.domain.DomainUtil;
import org.drumm.mosaic.kids.ratios.domain.KidsRoom;
import org.drumm.mosaic.kids.ratios.domain.KidsService;
import org.drumm.mosaic.kids.ratios.domain.KidsWeekend;
import org.drumm.mosaic.kids.ratios.domain.WeekendRatios;
import org.drumm.mosaic.kids.ratios.store.IKidsRatioStore;
import org.drumm.mosaic.kids.ratios.store.IKidsRoomStore;

public class KidsRatioService implements IKidsRatioService {
	
	private IKidsRoomStore roomStore;
	private IKidsRatioStore ratioStore;

	public KidsRatioService(IKidsRoomStore roomStore, IKidsRatioStore ratioStore){
		this.roomStore= roomStore;
		this.ratioStore=ratioStore;
	}

	@Override
	public void addWeekend(KidsWeekend weekend) {
		Collection<KidsRoom> rooms = weekend.toRooms();
		roomStore.putKidsRooms(rooms);
	}

	@Override
	public void addService(KidsService service) {
		Collection<KidsRoom> rooms = service.toRooms();
		roomStore.putKidsRooms(rooms);
	}

	@Override
	public void addRoom(KidsRoom room) {
		roomStore.putKidsRoom(room);
	}

	@Override
	public Map<String, KidsWeekend> getWeekends(long startDate, long endDate) {
		Collection<KidsRoom> rooms = roomStore.getKidsRooms(startDate, endDate);
		Map<String, KidsWeekend> weekends = DomainUtil.convertToMultipleWeekends(rooms);
		return weekends;
	}

	@Override
	public Map<String, KidsService> getServices(long startDate, long endDate) {
		Collection<KidsRoom> rooms = roomStore.getKidsRooms(startDate, endDate);
		Map<String, KidsService> services = DomainUtil.convertToMultipleServices(rooms);
		return services;
	}
	
	@Override
	public Collection<KidsRoom> getRooms(long startDate, long endDate) {
		Collection<KidsRoom> rooms = roomStore.getKidsRooms(startDate, endDate);
		return rooms;
	}

	@Override
	public WeekendRatios getRatios(long maxDateMillis) {
		return ratioStore.getRatioPriorToDate(maxDateMillis);
	}

}
