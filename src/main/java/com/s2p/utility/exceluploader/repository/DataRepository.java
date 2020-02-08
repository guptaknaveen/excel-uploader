package com.s2p.utility.exceluploader.repository;

import com.s2p.utility.exceluploader.model.Data;

public interface DataRepository extends Repository {
    Data save(Data data);
    void removeByMetaDataId(String metaDataId);
    Data findByMetaDataId(String metaDataId);
}
