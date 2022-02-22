package com.yz.mongodemo.mongo;

import com.mongodb.*;

/**
 * Java + MongoDB Hello world Example
 */
public class mongoTest {
    public static void main(String[] args) {

        try {

            /**** Connect to MongoDB ****/
            // Since 2.10.0, uses MongoClient
            MongoClient mongo = new MongoClient("localhost", 16161);

            /**** Get database ****/
            // if database doesn't exists, MongoDB will create it for you
            DB db = mongo.getDB("skedu");

            //DBCollection coll = (DBCollection) db.createCollection("student");

            /**** Get collection / table from 'testdb' ****/
            // if collection doesn't exists, MongoDB will create it for you
            DBCollection table = db.getCollection("student");


            /**** Insert ****/
            // create a document to store key and value
            BasicDBObject document = new BasicDBObject();
            document.put("sname", "mkyong");
            document.put("sage", 18);
            document.put("sex", "man");
            table.insert(document);

            /**** Find and display ****/
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("sname", "mkyong");

            DBCursor cursor = table.find(searchQuery);

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            /**** Update ****/
            // search document where name="mkyong" and update it with new values
            BasicDBObject query = new BasicDBObject();
            query.put("sname", "mkyong");

            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("sname", "mkyong-updated");

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);

            table.update(query, updateObj);


            /**** Find and display ****/
            System.out.println("111111111111");
            BasicDBObject searchQuery2
                    = new BasicDBObject().append("sname", "mkyong-updated");

            DBCursor cursor2 = table.find(searchQuery2);

            while (cursor2.hasNext()) {
                System.out.println("111111111111");

                System.out.println(cursor2.next());
            }

            /**** Done ****/
            System.out.println("Done");

        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}
