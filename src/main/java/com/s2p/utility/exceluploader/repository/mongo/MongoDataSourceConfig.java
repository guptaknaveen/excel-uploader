package com.s2p.utility.exceluploader.repository.mongo;

import com.mongodb.ServerAddress;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoDataSourceConfig {
    @Value("${spring.data.mongodb.port:27017}")
    private int port;

    @Value("${spring.data.mongodb.host:localhost}")
    private String host;

    @Value("${spring.data.mongodb.dbName:configDB}")
    private String dbName;

    private MongoDbFactory factory;

    public String getDatabaseName() {
        return dbName;
    }

    public MongoClient mongoClient() {
        ServerAddress address = new ServerAddress(host, port);

//        MongoCredential credential = MongoCredential.createCredential("", getDatabaseName(), new char[]{});
//        MongoClientOptions options = new MongoClientOptions.Builder().build();

        MongoClient client = new MongoClient(address);
        return client;
    }

    public MongoDbFactory mongoDbFactory() {
        if (factory == null) {
            factory = new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
        }
        return factory;
    }

    @Bean (name = "mongoDataSource")
    public MongoTemplate mongoTemplate() {
        MongoTemplate template = new MongoTemplate(mongoDbFactory());
        return template;
    }
}