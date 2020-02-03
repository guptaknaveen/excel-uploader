package com.s2p.utility.exceluploader.service;

import com.s2p.utility.exceluploader.model.Data;

import java.io.File;
import java.io.IOException;

public interface DataManager {
    Data saveDataFromExcel(String metaDataName, File file) throws IOException;
    Data fetchDataByMetaDataName(String metaDataName);
}
