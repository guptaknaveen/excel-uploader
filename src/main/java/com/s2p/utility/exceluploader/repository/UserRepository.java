package com.s2p.utility.exceluploader.repository;

import com.s2p.utility.exceluploader.model.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

public class UserRepository extends SimpleMongoRepository<User, String> {
    public UserRepository(MongoEntityInformation<User, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }
}
