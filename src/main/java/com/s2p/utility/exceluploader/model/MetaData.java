package com.s2p.utility.exceluploader.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Document
public class MetaData extends CommonModel {
    private String name;
    private String description;
    private List<Field> fields;

    public MetaData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Map getInfo() {
        return info;
    }

    public void setInfo(Map info) {
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MetaData addField(Field field) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        this.fields.add(field);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MetaData metaData = (MetaData) o;
        return id == metaData.id &&
                name.equals(metaData.name) &&
                fields.equals(metaData.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, fields);
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fields=" + fields +
                ", id='" + id + '\'' +
                ", info=" + info +
                '}';
    }
}
