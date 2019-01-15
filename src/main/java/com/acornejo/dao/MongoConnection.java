package com.acornejo.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan
public class MongoConnection {

    @Bean
    public static Datastore getConnection(){
        final Morphia morphia = new Morphia();
        morphia.mapPackage("src.main.java.com.acornejo.pojo");
        final Datastore datastore = morphia.createDatastore(new MongoClient(new MongoClientURI("mongodb://localhost:27017")), "biblioteca");
        datastore.ensureIndexes();
        return datastore;
    }
}
