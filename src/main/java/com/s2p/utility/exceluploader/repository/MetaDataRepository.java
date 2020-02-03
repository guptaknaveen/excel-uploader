package com.s2p.utility.exceluploader.repository;

import com.s2p.utility.exceluploader.model.MetaData;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;

public class MetaDataRepository extends SimpleMongoRepository<MetaData, String> {
    private MongoOperations mongoOperations;
    public MetaDataRepository(MongoEntityInformation<MetaData, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
    }

    public MetaData findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        List<MetaData> metaDataList = this.mongoOperations.find(query, MetaData.class);
        if (metaDataList != null && metaDataList.size() == 0) {
            return metaDataList.get(0);
        } else {
            return null;
        }
    }
}