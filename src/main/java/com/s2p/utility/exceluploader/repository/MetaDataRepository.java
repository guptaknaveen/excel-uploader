package com.s2p.utility.exceluploader.repository;

import com.s2p.utility.exceluploader.model.MetaData;

import java.util.List;

public interface MetaDataRepository extends Repository {
    MetaData findByName(String name);
    MetaData save(MetaData metaData);
    List<MetaData> fetchAll();
}