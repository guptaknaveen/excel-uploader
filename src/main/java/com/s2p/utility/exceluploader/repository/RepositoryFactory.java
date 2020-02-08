package com.s2p.utility.exceluploader.repository;

public interface RepositoryFactory {
    UserRepository getUserRepository();
    MetaDataRepository getMetaDataRepository();
    DataRepository getDataRepository();
}
