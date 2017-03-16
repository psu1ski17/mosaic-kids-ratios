package org.drumm.mosaic.kids.ratios.store.mongo;

import java.util.Map;
import java.util.Map.Entry;

import org.bson.Document;
import org.drumm.mosaic.kids.ratios.domain.RoomRatio;
import org.drumm.mosaic.kids.ratios.domain.WeekendRatios;
import org.drumm.mosaic.kids.ratios.store.IKidsRatioStore;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class MongoRatioStore extends BaseMongoStore implements IKidsRatioStore {

	public MongoRatioStore(MongoCollectionProvider factory,
			String collectionName) {
		super(factory, collectionName);
	}

	@Override
	public WeekendRatios getLatestRatio() {
		return getRatioPriorToDate(System.currentTimeMillis());
	}

	@Override
	public WeekendRatios getRatioPriorToDate(long priorToDateMillis) {
		return getRatiosPriorToDateByIteratingThroughAll(priorToDateMillis);
	}

	@Override
	public void addRatios(WeekendRatios ratios) {
		Map<String, Map<String, RoomRatio>> map = ratios.getMap();
		for (Entry<String, Map<String, RoomRatio>> entry : map.entrySet()) {
			Map<String, RoomRatio> rows = entry.getValue();
			if (rows != null) {
				for (Entry<String, RoomRatio> e : rows.entrySet()) {
					RoomRatio ratio = e.getValue();
					addRatio(ratio);
				}
			}
		}
	}

	@Override
	public void addRatio(RoomRatio ratio) {
		Document doc = new Document(RoomRatio.DATE, ratio.getDateSet());
		doc.put(RoomRatio.KIDS, ratio.getKids());
		doc.put(RoomRatio.VOLUNTEERS, ratio.getVolunteers());
		doc.put(RoomRatio.SERVICE, ratio.getService());
		doc.put(RoomRatio.ROOM, ratio.getRoom());
		collection.insertOne(doc);
	}

	// TODO this is ineffecient, but simple and not really a problem at the
	// moment with no way to change ratios. EVentually we should create a better
	// implementation.
	private WeekendRatios getRatiosPriorToDateByIteratingThroughAll(long maxTimeMills) {
		WeekendRatios ratios = new WeekendRatios();
		FindIterable<Document> iterable = collection.find();
		MongoCursor<Document> itr = iterable.iterator();
		while (itr.hasNext()) {
			Document doc = itr.next();
			RoomRatio ratio = docToRatio(doc);
			if (ratio.getDateSet() > maxTimeMills) {
				continue;
			}
			RoomRatio oldRatio = ratios
					.get(ratio.getService(), ratio.getRoom());
			if (oldRatio.getDateSet() < ratio.getDateSet()) {
				ratios.put(ratio);
			}
		}
		return ratios;
	}

	private RoomRatio docToRatio(Document doc) {
		RoomRatio ratio = new RoomRatio();
		ratio.setDateSet(doc.getLong(RoomRatio.DATE));
		ratio.setVolunteers(doc.getInteger(RoomRatio.VOLUNTEERS));
		ratio.setKids(doc.getInteger(RoomRatio.KIDS));
		ratio.setRoom(doc.getString(RoomRatio.ROOM));
		ratio.setService(doc.getString(RoomRatio.SERVICE));
		return ratio;
	}

}
