package org.drumm.mosaic.kids.ratios.service;

import java.util.Collection;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.drumm.mosaic.kids.ratios.domain.KidsRoom;
import org.drumm.mosaic.kids.ratios.domain.KidsService;
import org.drumm.mosaic.kids.ratios.domain.KidsWeekend;
import org.drumm.mosaic.kids.ratios.domain.RoomRatio;
import org.drumm.mosaic.kids.ratios.domain.WeekendRatios;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KidsRatioResource {

	private IKidsRatioService ratioService;

	public KidsRatioResource(IKidsRatioService ratioService) {
		this.ratioService = ratioService;
	}

	@GET
	@Path("/weekend")
	public Map<String, KidsWeekend> getWeekends(
			@DefaultValue("-1") @QueryParam("startDate") long startDate,
			@DefaultValue("-1") @QueryParam("endDate") long endDate) {
		try {
			if (endDate<0){
				endDate=System.currentTimeMillis()+365*24*60*60*1000;
			}
			return ratioService.getWeekends(startDate, endDate);
		} catch (Exception e) {
			// create smart webapp exception
		}
		return null;
	}

	@GET
	@Path("/rooms")
	public Collection<KidsRoom> getRooms(
			@DefaultValue("-1") @QueryParam("startDate") long startDate,
			@DefaultValue("-1") @QueryParam("endDate") long endDate) {
		try {
			if (endDate<0){
				endDate=System.currentTimeMillis()+365*24*60*60*1000;
			}
			return ratioService.getRooms(startDate, endDate);
		} catch (Exception e) {
			// create smart webapp exception
		}
		return null;
	}

	
	@POST
	@Path("/weekend")
	public void postWeekend(KidsWeekend weekend) {
		try {
			ratioService.addWeekend(weekend);
		} catch (Exception e) {
			// create smart webapp exception
		}
	}
	
	@GET
	@Path("/service")
	public Map<String, KidsService> getServices(
			@DefaultValue("-1") @QueryParam("startDate") long startDate,
			@DefaultValue("-1") @QueryParam("endDate") long endDate) {
		try {
			if (endDate<0){
				endDate=System.currentTimeMillis()+365*24*60*60*1000;
			}
			return ratioService.getServices(startDate, endDate);
		} catch (Exception e) {
			// create smart webapp exception
		}
		return null;
	}
	
	@POST
	@Path("/service")
	public void postWeekend(KidsService service) {
		try {
			ratioService.addService(service);
		} catch (Exception e) {
			// create smart webapp exception
		}
	}
	
	@POST
	@Path("/room")
	public void postWeekend(KidsRoom room) {
		try {
			ratioService.addRoom(room);
		} catch (Exception e) {
			// create smart webapp exception
		}
	}
	
	@GET
	@Path("/ratio")
	public Map<String, Map<String, RoomRatio>> getRatios(@DefaultValue("-1") @QueryParam("maxDateMillis") long maxDateMillis){
		try {
			if (maxDateMillis<0){
				maxDateMillis=System.currentTimeMillis();
			}
			return ratioService.getRatios(maxDateMillis).getServiceToRoomToRatio();
		} catch (Exception e) {
			// create smart webapp exception
		}
		return null;
	}
}
