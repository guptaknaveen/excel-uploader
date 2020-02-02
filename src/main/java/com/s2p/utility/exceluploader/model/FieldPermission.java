package com.s2p.utility.exceluploader.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class FieldPermission extends CommonModel {
    private Field field;
    private int permission;

    public FieldPermission(Field field, int permission) {
        this.field = field;
        this.permission = permission;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FieldPermission that = (FieldPermission) o;
        return permission == that.permission &&
                field.equals(that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), field, permission);
    }

    @Override
    public String toString() {
        return "FieldPermission{" +
                "field=" + field +
                ", permission=" + permission +
                '}';
    }
}
