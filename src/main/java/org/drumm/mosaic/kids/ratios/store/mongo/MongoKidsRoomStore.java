package org.drumm.mosaic.kids.ratios.store.mongo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.drumm.mosaic.kids.ratios.domain.KidsRoom;
import org.drumm.mosaic.kids.ratios.store.IKidsRoomStore;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.UpdateOptions;

public class MongoKidsRoomStore  extends BaseMongoStore implements IKidsRoomStore {
	
	public MongoKidsRoomStore(MongoCollectionProvider provider, String collectionName) {
		super(provider, collectionName);
	}
	
	public MongoKidsRoomStore(MongoCollectionProvider provider) {
		super(provider, "test");
	}

	@Override
	public void putKidsRoom(KidsRoom kidsRoom) {
		String id = getId(kidsRoom);
		Document doc = new Document(id, kidsRoom);
		UpdateOptions updateOptions = new UpdateOptions();
		updateOptions.upsert(true);
		BsonDocument filter = new BsonDocument(MONGO_ID, new BsonString(id));
		doc = roomToDocument(kidsRoom, id);
		collection.replaceOne(filter, doc, updateOptions);

	}

	@Override
	public void putKidsRooms(Collection<KidsRoom> kidsRooms) {
		Iterator<KidsRoom> itr = kidsRooms.iterator();
		while (itr.hasNext()) {
			KidsRoom room = itr.next();
			putKidsRoom(room);
		}
	}

	@Override
	public Collection<KidsRoom> getKidsRooms(long startTime, long endTime) {
		List<KidsRoom> rooms = new ArrayList<KidsRoom>();
		Document filter = new Document("time",
				new Document("$gte", startTime).append("$lt", endTime));
		MongoCursor<Document> itr = collection.find(filter).iterator();
		while (itr.hasNext()) {
			Document doc = itr.next();
			KidsRoom room = documentToRoom(doc);
			rooms.add(room);
		}
		return rooms;
	}

	private static KidsRoom documentToRoom(Document doc) {
		KidsRoom room = new KidsRoom();
		room.setTime(doc.getLong(KidsRoom.TIME));
		room.setCampus(doc.getString(KidsRoom.CAMPUS));
		room.setRoom(doc.getString(KidsRoom.ROOM));
		room.setService(doc.getString(KidsRoom.SERVICE));
		room.setWeekend(doc.getString(KidsRoom.WEEKEND));
		room.setNumKids(doc.getInteger(KidsRoom.NUM_KIDS));
		room.setNumVolunteers(doc.getInteger(KidsRoom.NUM_VOLUNTEERS));
		return room;
	}

	// There has got to be a different way, more resilient to changes to the
	// domain objects, but for now i just need something that works. Also, this
	// could probably be more efficient
	private static Document roomToDocument(KidsRoom room, String id) {
		Document doc = new Document();
		doc.put(KidsRoom.CAMPUS, room.getCampus());
		doc.put(KidsRoom.ROOM, room.getRoom());
		doc.put(KidsRoom.WEEKEND, room.getWeekend());
		doc.put(KidsRoom.SERVICE, room.getService());
		doc.put(KidsRoom.TIME, room.getTime());
		doc.put(KidsRoom.NUM_KIDS, room.getNumKids());
		doc.put(KidsRoom.NUM_VOLUNTEERS, room.getNumVolunteers());
		doc.put(MONGO_ID, id);
		return doc;
	}

	private static String getId(KidsRoom kidsRoom) {
		return kidsRoom.getTime() + "-" + kidsRoom.getRoom() + "-"
				+ kidsRoom.getCampus();
	}

	public static void main(String[] args) {
		KidsRoom room = new KidsRoom("room1", "service1", "weekend1", 5, 1, "campus1", System.currentTimeMillis());
		Document doc = roomToDocument(room, "1");
		KidsRoom room2 = documentToRoom(doc);
		Document doc2 = roomToDocument(room2, "1");
		KidsRoom room3 = documentToRoom(doc2);
		System.out.println(room);
		System.out.println(room3);
	}
}
