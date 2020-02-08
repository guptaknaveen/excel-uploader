package com.s2p.utility.exceluploader.repository.mongo;

import com.s2p.utility.exceluploader.model.Data;
import com.s2p.utility.exceluploader.model.User;
import com.s2p.utility.exceluploader.repository.DataRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;

public class DataRepositoryImpl implements DataRepository {
    private MongoOperations mongoOperations;
    private SimpleMongoRepository<Data, String> defaultMongoRepository;

    public DataRepositoryImpl(MongoEntityInformation<Data, String> metadata, MongoOperations mongoOperations) {
        this.defaultMongoRepository = new SimpleMongoRepository<>(metadata, mongoOperations);
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

    @Override
    public Data save(Data data) {
        return this.defaultMongoRepository.save(data);
    }
}
