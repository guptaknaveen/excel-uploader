package com.s2p.utility.exceluploader.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Document
public class Data extends CommonModel {

    private String metaDataId;

    private List<DataRow> dataRows;

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

    public Data(String metaDataId) {
        this.metaDataId = metaDataId;
    }

    public String getMetaDataId() {
        return metaDataId;
    }

    public void setMetaDataId(String metaDataId) {
        this.metaDataId = metaDataId;
    }

    public List<DataRow> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<DataRow> dataRows) {
        this.dataRows = dataRows;
    }

    public void addDataRow(DataRow dataRow) {
        if (this.dataRows == null) {
            this.dataRows = new ArrayList<>();
        }
        this.dataRows.add(dataRow);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Data data = (Data) o;
        return metaDataId.equals(data.metaDataId) &&
                dataRows.equals(data.dataRows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), metaDataId, dataRows);
    }

    @Override
    public String toString() {
        return "Data{" +
                "metaDataId='" + metaDataId + '\'' +
                ", dataRows=" + dataRows +
                ", id='" + id + '\'' +
                ", info=" + info +
                '}';
    }
}
