package com.s2p.utility.exceluploader.repository;

import com.s2p.utility.exceluploader.model.Data;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;

public class DataRepository extends SimpleMongoRepository<Data, String> {
    private MongoOperations mongoOperations;

    public DataRepository(MongoEntityInformation<Data, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
    }

    public Data findByMetaDataId(String metaDataId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("metaDataId").is(metaDataId));
        List<Data> dataList = this.mongoOperations.find(query, Data.class);
        if (dataList == null) {
            return null;
        } else {
            return dataList.get(0);
        }
    }

    public void removeByMetaDataId(String metaDataId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("metaDataId").is(metaDataId));
        this.mongoOperations.remove(query, Data.class);
    }
}
