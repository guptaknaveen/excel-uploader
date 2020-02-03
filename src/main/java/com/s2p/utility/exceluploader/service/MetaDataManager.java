package com.s2p.utility.exceluploader.service;

import com.s2p.utility.exceluploader.model.MetaData;

import java.io.File;
import java.io.IOException;

public interface MetaDataManager {
    MetaData addMetaData(MetaData metaData);
    MetaData fetchMetaData(String name);
    MetaData readMetaDataFromExcel(File excelFile) throws IOException;
    MetaData saveMetaDataFromExcel(File excelFile) throws IOException;
    MetaData saveMetaDataFromExcel(String metaDataName, File excelFile) throws IOException;
}
