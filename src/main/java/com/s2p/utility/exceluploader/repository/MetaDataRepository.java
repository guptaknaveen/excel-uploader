package com.s2p.utility.exceluploader.repository;

import com.s2p.utility.exceluploader.model.MetaData;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

public class MetaDataRepository extends SimpleMongoRepository<MetaData, String> {
    public MetaDataRepository(MongoEntityInformation<MetaData, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }
}