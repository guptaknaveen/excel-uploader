package com.s2p.utility.exceluploader.model;

import com.s2p.utility.exceluploader.constant.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Document
public class Field extends CommonModel {
    private int order;
    private String name;
    private String metaData;
    private FieldType fieldType;
    private List<String> permissions;

    public Field() {
    }

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

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public FieldType getFieldType(String fieldType) {
        return FieldType.STRING.getFieldTypeFromString(fieldType);
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(String permission) {
        if(this.permissions == null) {
            this.permissions = new ArrayList<>();
        }
        this.permissions.add(permission);
    }

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Field field = (Field) o;
        return order == field.order &&
                name.equals(field.name) &&
                metaData.equals(field.metaData) &&
                fieldType == field.fieldType &&
                permissions.equals(field.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), order, name, metaData, fieldType, permissions);
    }

    @Override
    public String toString() {
        return "Field{" +
                "order=" + order +
                ", name='" + name + '\'' +
                ", metaData='" + metaData + '\'' +
                ", fieldType=" + fieldType +
                ", permissions=" + permissions +
                ", id='" + id + '\'' +
                ", info=" + info +
                '}';
    }
}
