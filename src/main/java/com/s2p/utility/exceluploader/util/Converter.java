package com.s2p.utility.exceluploader.util;

import com.s2p.utility.exceluploader.model.*;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public class Converter {
    private Converter() {

    }
    private static Converter converter = new Converter();

    public static Converter getConverter() {
        return converter;
    }

    public TableData getTableDataFromMetaData(MetaData metaData, Data data) {
        TableData tableData = new TableData();
        Map<String, String> fieldIdMap = new HashMap<>();
        for (Field field : metaData.getFields()) {
            tableData.addHeader(field.getName().toUpperCase());
            fieldIdMap.put(field.getId(), field.getName().toUpperCase());
        }
        tableData.addHeader("_ID");
        fieldIdMap.put("_id", "_ID");

        if (data == null || CollectionUtils.isEmpty(data.getDataRows())) {
            return tableData;
        }

        for (DataRow dataRow : data.getDataRows()) {
            tableData.addData(getRowMap(dataRow, fieldIdMap));
        }
        return tableData;
    }

    private Map<String, String> getRowMap(DataRow dataRow, Map<String, String> fieldIdMap) {
        Map<String, String> rowMap = new HashMap<>();
        for (FieldData fieldData : dataRow.getFieldData()) {
            rowMap.put(fieldIdMap.get(fieldData.getFieldId()), fieldData.getValue() == null ? "" : fieldData.getValue().toString());
        }
        rowMap.put(fieldIdMap.get("_id"), dataRow.getId());
        return rowMap;
    }
}
