package org.drumm.mosaic.kids.ratios.store.mongo;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoCollectionProvider implements Closeable {
	protected MongoClient mongoClient;
	protected MongoDatabase database;

	public MongoCollectionProvider(String host, int port, String databaseName,
			String userName, String password) {

		MongoClientOptions options = MongoClientOptions.builder().build();
		MongoCredential credential = MongoCredential.createCredential(userName,
				databaseName, password.toCharArray());
		ArrayList<MongoCredential> credentials = new ArrayList<MongoCredential>(
				1);
		credentials.add(credential);
		ServerAddress addr = new ServerAddress(host, port);
		this.mongoClient = new MongoClient(addr, credentials, options);
		this.database = mongoClient.getDatabase(databaseName);

	}

	public MongoCollection<Document> getCollection(String collectionName) {
		MongoCursor<String> itr = database.listCollectionNames().iterator();
		boolean exists = false;
		while (itr.hasNext()) {
			String colName = itr.next();
			if (colName.equals(collectionName)) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			database.createCollection(collectionName);
		}
		return database.getCollection(collectionName);

	}

	@Override
	public void close() throws IOException {
		mongoClient.close();
	}
}
