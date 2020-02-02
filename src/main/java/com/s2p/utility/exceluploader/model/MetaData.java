package com.s2p.utility.exceluploader.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Document
public class MetaData extends CommonModel {
    private String name;
    private String label;
    private String description;
    private List<String> tags;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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

    public MetaData addTag(String tag) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tag);
        return this;
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
                label.equals(metaData.label) &&
                tags.equals(metaData.tags) &&
                fields.equals(metaData.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, label, tags, fields);
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", fields=" + fields +
                ", id='" + id + '\'' +
                ", info=" + info +
                '}';
    }
}
