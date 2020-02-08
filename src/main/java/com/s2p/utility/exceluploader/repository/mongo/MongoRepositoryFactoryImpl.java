package com.s2p.utility.exceluploader.repository.mongo;

import com.s2p.utility.exceluploader.model.Data;
import com.s2p.utility.exceluploader.model.MetaData;
import com.s2p.utility.exceluploader.model.User;
import com.s2p.utility.exceluploader.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MongoRepositoryFactoryImpl implements RepositoryFactory {
    private static final String USER_COLLECTION_NAME = "user";
    private static final String DATA_COLLECTION_NAME = "data";
    private static final String META_DATA_COLLECTION_NAME = "metadata";

    @Autowired
    @Qualifier("mongoDataSourceConfig")
    private MongoDataSourceConfig mongoDataSourceConfig;

    private static Map<String, Repository> repositoryCache = new HashMap<>();

    public UserRepository getUserRepository() {
        if (repositoryCache.get(USER_COLLECTION_NAME) == null) {
            repositoryCache.put(USER_COLLECTION_NAME, new UserRepositoryImpl(
                    new MappingMongoEntityInformation<User, String>(
                           new BasicMongoPersistentEntity<User>(ClassTypeInformation.from(User.class))
                    ),
                    mongoDataSourceConfig.mongoTemplate()
            ));
        }
        return (UserRepository) repositoryCache.get(USER_COLLECTION_NAME);
    }


    public MetaDataRepository getMetaDataRepository() {
        if (repositoryCache.get(META_DATA_COLLECTION_NAME) == null) {
            repositoryCache.put(META_DATA_COLLECTION_NAME, new MetaDataRepositoryImpl(
                    new MappingMongoEntityInformation<MetaData, String>(
                            new BasicMongoPersistentEntity<MetaData>(ClassTypeInformation.from(MetaData.class))
                    ),
                    mongoDataSourceConfig.mongoTemplate()
            ));
        }
        return (MetaDataRepository) repositoryCache.get(META_DATA_COLLECTION_NAME);
    }

    public DataRepository getDataRepository() {
        if (repositoryCache.get(DATA_COLLECTION_NAME) == null) {
            repositoryCache.put(DATA_COLLECTION_NAME, new DataRepositoryImpl(
                    new MappingMongoEntityInformation<Data, String>(
                            new BasicMongoPersistentEntity<Data>(ClassTypeInformation.from(Data.class))
                    ),
                    mongoDataSourceConfig.mongoTemplate()
            ));
        }
        return (DataRepository) repositoryCache.get(DATA_COLLECTION_NAME);
    }
}
