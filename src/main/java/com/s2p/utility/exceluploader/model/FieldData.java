package com.s2p.utility.exceluploader.model;

import java.util.Objects;

public class FieldData {
    private String fieldId;
    private Object value;

    public FieldData(String fieldId, Object value) {
        this.fieldId = fieldId;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldData fieldData = (FieldData) o;
        return fieldId.equals(fieldData.fieldId) &&
                value.equals(fieldData.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldId, value);
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FieldData{" +
                "fieldId='" + fieldId + '\'' +
                ", value=" + value +
                '}';
    }
}
