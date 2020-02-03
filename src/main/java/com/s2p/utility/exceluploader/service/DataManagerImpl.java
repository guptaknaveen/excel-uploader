package com.s2p.utility.exceluploader.service;

import com.s2p.utility.exceluploader.model.*;
import com.s2p.utility.exceluploader.repository.RepositoryFactory;
import com.s2p.utility.exceluploader.util.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DataManagerImpl implements DataManager {
    @Autowired
    private RepositoryFactory repositoryFactory;

    @Autowired
    private MetaDataManager metaDataManager;

    @Override
    public Data saveDataFromExcel(String metaDataName, File file) throws IOException {
        MetaData metaData = metaDataManager.fetchMetaData(metaDataName);

        Data data = new Data(metaData.getId());

        data = fetchDataFromExcel(data, metaData, file);

        // remove all the data with the above metadata id
        repositoryFactory.getDataRepository().removeByMetaDataId(metaData.getId());

        return repositoryFactory.getDataRepository().save(data);
    }

    private Data fetchDataFromExcel(Data data, MetaData metaData, File file) throws IOException {
        List<Map<String, Object>> excelData = ExcelReader.getExcelReader().readXLSData(file);

        Map<String, Field> fieldMap = createFieldMap(metaData);

        for (Map<String, Object> rowMap : excelData) {
            data.addDataRow(createDataRow(rowMap, fieldMap));
        }

        return data;
    }

    private Map<String, Field> createFieldMap(MetaData metaData) {
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : metaData.getFields()) {
            fieldMap.put(field.getName().toLowerCase(), field);
        }
        return fieldMap;
    }

    private DataRow createDataRow(Map<String, Object> rowMap, Map<String, Field> fieldMap) {
        DataRow dataRow = new DataRow();

        for (Map.Entry<String, Object> entry : rowMap.entrySet()) {
            if (fieldMap.get(entry.getKey().toLowerCase()) == null) {
                continue;
            }
            dataRow.addFieldData(new FieldData(fieldMap.get(entry.getKey().toLowerCase()).getId(), entry.getValue()));
        }

        dataRow.setId(String.valueOf(UUID.randomUUID()));
        return dataRow;
    }

    @Override
    public Data fetchDataByMetaDataName(String metaDataName) {
        MetaData metadata = metaDataManager.fetchMetaData(metaDataName);
        if (metadata == null) {
            return null;
        }
        return repositoryFactory.getDataRepository().findByMetaDataId(metadata.getId());
    }
}
