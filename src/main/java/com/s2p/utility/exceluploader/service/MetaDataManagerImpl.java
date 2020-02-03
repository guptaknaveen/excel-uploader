package com.s2p.utility.exceluploader.service;

import com.s2p.utility.exceluploader.model.MetaData;
import com.s2p.utility.exceluploader.repository.MetaDataRepository;
import com.s2p.utility.exceluploader.repository.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MetaDataManagerImpl implements MetaDataManager {
    @Autowired
    private RepositoryFactory repositoryFactory;

    @Override
    public MetaData addMetaData(MetaData metaData) {
        MetaDataRepository repository = repositoryFactory.getMetaDataRepository();
        return repository.save(metaData);
    }

    @Override
    public MetaData fetchMetaData(String name) {
        return repositoryFactory.getMetaDataRepository().findByName(name);
    }
}
