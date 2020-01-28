package com.s2p.utility.exceluploader.model;

import com.s2p.utility.exceluploader.constant.FieldType;

import java.util.Objects;

public class Field extends CommonModel {
    private String name;
    private String collection;
    private FieldType fieldType;

    public Field(String name, FieldType fieldType) {
        this.name = name;
        this.fieldType = fieldType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Field field = (Field) o;
        return id == field.id &&
                name.equals(field.name) &&
                collection.equals(field.collection) &&
                fieldType == field.fieldType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, collection, fieldType);
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", collection='" + collection + '\'' +
                ", fieldType=" + fieldType +
                '}';
    }
}