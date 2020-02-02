package com.s2p.utility.exceluploader.repository;

import com.s2p.utility.exceluploader.model.Data;
import com.s2p.utility.exceluploader.model.MetaData;
import com.s2p.utility.exceluploader.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RepositoryFactory {
    private static final String USER_COLLECTION_NAME = "user";
    private static final String DATA_COLLECTION_NAME = "data";
    private static final String META_DATA_COLLECTION_NAME = "metadata";

    @Autowired
    private MongoTemplate mongoTemplate;

    private static Map<String, MongoRepository> repositoryCache = new HashMap<>();

    public UserRepository getUserRepository() {
        if (repositoryCache.get(USER_COLLECTION_NAME) == null) {
            repositoryCache.put(USER_COLLECTION_NAME, new UserRepository(
                    new MappingMongoEntityInformation<User, String>(
                           new BasicMongoPersistentEntity<User>(ClassTypeInformation.from(User.class))
                    ),
                    mongoTemplate
            ));
        }
        return (UserRepository) repositoryCache.get(USER_COLLECTION_NAME);
    }


    public MetaDataRepository getMetaDataRepository() {
        if (repositoryCache.get(META_DATA_COLLECTION_NAME) == null) {
            repositoryCache.put(META_DATA_COLLECTION_NAME, new MetaDataRepository(
                    new MappingMongoEntityInformation<MetaData, String>(
                            new BasicMongoPersistentEntity<MetaData>(ClassTypeInformation.from(MetaData.class))
                    ),
                    mongoTemplate
            ));
        }
        return (MetaDataRepository) repositoryCache.get(META_DATA_COLLECTION_NAME);
    }

    public DataRepository getDataRepository() {
        if (repositoryCache.get(DATA_COLLECTION_NAME) == null) {
            repositoryCache.put(DATA_COLLECTION_NAME, new DataRepository(
                    new MappingMongoEntityInformation<Data, String>(
                            new BasicMongoPersistentEntity<Data>(ClassTypeInformation.from(Data.class))
                    ),
                    mongoTemplate
            ));
        }
        return (DataRepository) repositoryCache.get(DATA_COLLECTION_NAME);
    }
}
