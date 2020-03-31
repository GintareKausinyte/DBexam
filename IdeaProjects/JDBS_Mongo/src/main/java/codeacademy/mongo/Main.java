package codeacademy.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[]args){
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://test:test@jdbsmongo-z6pc5.mongodb.net/test?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> mongoCollection= database.getCollection("zmones");
//        Document asmuo =new Document("vardas", "Gintare").append("pavarde", "Kausinyte");
//        mongoCollection.insertOne(asmuo);
//     //FIND A VALUE
//        Document result = mongoCollection.find(Filters.eq("vardas", "Gintare")).first();
//        System.out.println(result.toJson());
//    //  MongoClient.close();

        Document automobilis1=new Document().append("marke", "BMW").append("spalva", "zalia").append("durys", "4");
        Document automobilis2=new Document().append("marke", "Audi").append("spalva", "raudona").append("durys", "4");
        Document automobilis3=new Document().append("marke", "VW").append("spalva", "geltona").append("durys", "4");
        ArrayList<Document> documents= new ArrayList<Document>();
        documents.add(automobilis1);
        documents.add(automobilis2);
        documents.add(automobilis3);
        MongoCollection<Document> autoKolekcija=database.getCollection("automobiliai");
        autoKolekcija.insertMany(documents);

    }
}
