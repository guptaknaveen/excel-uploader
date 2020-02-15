package com.s2p.utility.exceluploader.repository.mongo;

import com.mongodb.ServerAddress;
import com.mongodb.MongoClient;
import com.s2p.utility.exceluploader.logger.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoDataSourceConfig {
    private Logger logger = Logger.getLogger();

    @Value("${spring.data.mongodb.port:27017}")
    private int port;

    @Value("${spring.data.mongodb.host:localhost}")
    private String host;

    @Value("${spring.data.mongodb.database:configDataDB}")
    private String dbName;

    private MongoDbFactory factory;

    private MongoTemplate mongoTemplate;

    private MongoClient mongoClient() {
        ServerAddress address = new ServerAddress(host, port);
        MongoClient client = new MongoClient(address);
        return client;
    }

    private MongoDbFactory mongoDbFactory() {
        if (factory == null) {
            factory = new SimpleMongoDbFactory(mongoClient(), dbName);
        }
        return factory;
    }

    public MongoTemplate mongoTemplate() {
        if (mongoTemplate == null) {
            try {
                mongoTemplate = new MongoTemplate(mongoDbFactory());
                logger.info("Database connection created with " + toString());
                return mongoTemplate;
            } catch (Exception e) {
                logger.info("Exception occurred during the database connection due to "
                        + e.getMessage() + " please try again for database connection");
            }
        }
        return mongoTemplate;
    }

    @Override
    public String toString() {
        return "MongoDataSourceConfig{" +
                "port=" + port +
                ", host='" + host + '\'' +
                ", dbName='" + dbName + '\'' +
                '}';
    }
}