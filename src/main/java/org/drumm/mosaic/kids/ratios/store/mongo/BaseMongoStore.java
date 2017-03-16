package org.drumm.mosaic.kids.ratios.store.mongo;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public abstract class BaseMongoStore {
	public final static String MONGO_ID="_id";
	protected MongoCollection<Document> collection;

	public BaseMongoStore(MongoCollectionProvider factory, String collectionName) {
		collection = factory.getCollection(collectionName);
	}
}
