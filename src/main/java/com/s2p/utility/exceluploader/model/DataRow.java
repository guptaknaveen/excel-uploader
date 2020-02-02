package com.s2p.utility.exceluploader.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Document
public class DataRow extends CommonModel {
    private List<FieldData> fieldData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map getInfo() {
        return info;
    }

    public void setInfo(Map info) {
        this.info = info;
    }

    public List<FieldData> getFieldData() {
        return fieldData;
    }

    public void setFieldData(List<FieldData> fieldData) {
        this.fieldData = fieldData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DataRow dataRow = (DataRow) o;
        return fieldData.equals(dataRow.fieldData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldData);
    }

    @Override
    public String toString() {
        return "DataRow{" +
                "fieldData=" + fieldData +
                ", id='" + id + '\'' +
                ", info=" + info +
                '}';
    }
}
