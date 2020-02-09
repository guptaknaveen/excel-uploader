package com.s2p.utility.exceluploader.repository.mongo;

import com.s2p.utility.exceluploader.model.MetaData;
import com.s2p.utility.exceluploader.repository.MetaDataRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;

public class MetaDataRepositoryImpl implements MetaDataRepository {
    private MongoOperations mongoOperations;
    private SimpleMongoRepository<MetaData, String> defaultMongoRepository;
    public MetaDataRepositoryImpl(MongoEntityInformation<MetaData, String> metadata, MongoOperations mongoOperations) {
        this.defaultMongoRepository = new SimpleMongoRepository<>(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
    }

    public MetaData findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        List<MetaData> metaDataList = this.mongoOperations.find(query, MetaData.class);
        if (metaDataList == null||metaDataList.size() == 0) {
            return null;
        } else {
            return metaDataList.get(0);
        }
    }

    @Override
    public MetaData save(MetaData metaData) {
        return this.defaultMongoRepository.save(metaData);
    }

    @Override
    public List<MetaData> fetchAll() {
        return this.defaultMongoRepository.findAll();
    }
}