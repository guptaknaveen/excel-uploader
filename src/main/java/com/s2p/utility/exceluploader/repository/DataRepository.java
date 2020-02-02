package com.s2p.utility.exceluploader.repository;

import com.s2p.utility.exceluploader.model.Data;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

public class DataRepository extends SimpleMongoRepository<Data, String> {
    public DataRepository(MongoEntityInformation<Data, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }
}
