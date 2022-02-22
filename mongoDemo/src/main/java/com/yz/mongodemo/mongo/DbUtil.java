package com.yz.mongodemo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.springframework.stereotype.Component;

/**
 * DbUtil
 */
@Component
public class DbUtil {

    public static MongoDatabase db;
    private MongoClient mongoClient =new MongoClient("192.168.3.228 ");
    public DbUtil () {

    }

    public MongoCollection<Document> getCollection (String collectionName) {
        return db.getCollection(collectionName);
    }
    public MongoIterable<String> listCollectionNames () {
        return db.listCollectionNames();
    }
    
    public void init (String dbName) {
    	
        //mongoClient.getDatabase(dbName).drop();
        db = mongoClient.getDatabase(dbName);
    }
    
    public void init (String dbName, boolean delDbFlag) {
        if (delDbFlag) mongoClient.getDatabase(dbName).drop();
        db = mongoClient.getDatabase(dbName);
    }

    // public void open () {
    //     db = mongoClient.getDatabase("jcl");
    // }

    public void close () {
        mongoClient.close();
    }
}