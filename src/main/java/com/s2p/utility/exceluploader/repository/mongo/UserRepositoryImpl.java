package com.s2p.utility.exceluploader.repository.mongo;

import com.s2p.utility.exceluploader.model.User;
import com.s2p.utility.exceluploader.repository.UserRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

public class UserRepositoryImpl implements UserRepository {
    private MongoOperations mongoOperations;
    private SimpleMongoRepository<User, String> defaultMongoRepository;
    public UserRepositoryImpl(MongoEntityInformation<User, String> metadata, MongoOperations mongoOperations) {
        this.defaultMongoRepository = new SimpleMongoRepository<>(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
    }

    @Override
    public User save(User user) {
        return this.defaultMongoRepository.save(user);
    }
}
