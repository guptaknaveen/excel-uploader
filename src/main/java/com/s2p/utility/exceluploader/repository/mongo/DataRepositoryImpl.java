package com.s2p.utility.exceluploader.repository.mongo;

import com.s2p.utility.exceluploader.model.Data;
import com.s2p.utility.exceluploader.repository.DataRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;
import java.util.Optional;

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
        if (dataList == null || dataList.size() == 0) {
            return null;
        } else {
            return dataList.get(0);
        }
    }

    @Override
    public Data fetchDataByDataId(String dataId) {
        Optional<Data> data = this.defaultMongoRepository.findById(dataId);
        return data.isPresent() ? data.get() : null;
    }

    @Override
    public void removeData(Data data) {
        this.defaultMongoRepository.delete(data);
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
