package com.s2p.utility.exceluploader.service;

import com.s2p.utility.exceluploader.constant.FieldType;
import com.s2p.utility.exceluploader.constant.Permission;
import com.s2p.utility.exceluploader.model.Field;
import com.s2p.utility.exceluploader.model.MetaData;
import com.s2p.utility.exceluploader.repository.MetaDataRepository;
import com.s2p.utility.exceluploader.repository.RepositoryFactory;
import com.s2p.utility.exceluploader.util.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Override
    public MetaData readMetaDataFromExcel(File excelFile) throws IOException {
        List<Map<String, Object>> data = ExcelReader.getExcelReader().readXLSData(excelFile);
        MetaData metaData = new MetaData(excelFile.getName().substring(0, excelFile.getName().lastIndexOf('.')));
        for(Map<String, Object> rowMap : data) {
            metaData.addField(createFieldFromRowMap(rowMap));
        }
        return metaData;
    }

    @Override
    public MetaData saveMetaDataFromExcel(File excelFile) throws IOException {
        return addMetaData(readMetaDataFromExcel(excelFile));
    }

    @Override
    public MetaData saveMetaDataFromExcel(String metaDataName, File excelFile) throws IOException {
        MetaData metaData = readMetaDataFromExcel(excelFile);
        metaData.setName(metaDataName);
        return addMetaData(metaData);
    }

    private Field createFieldFromRowMap(Map<String, Object> rowMap) {
        Field field = new Field();
        field.setName((String) rowMap.get("NAME"));
        field.setOrder(((Double) rowMap.get("ORDER")).intValue());
        field.setFieldType(FieldType.STRING.getFieldTypeFromString((String) rowMap.get("FIELD_TYPE")));
        if (rowMap.get("VIEW") != null) {
            field.addPermission(Permission.VIEW);
        }
        if (rowMap.get("EDIT") != null) {
            field.addPermission(Permission.EDIT);
        }
        field.setId(String.valueOf(UUID.randomUUID()));
        return field;
    }
}
