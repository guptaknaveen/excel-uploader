package com.s2p.utility.exceluploader.constant;

public enum FieldType {
    DATE,
    STRING,
    NUMBER,
    TEXT;

    public FieldType getFieldTypeFromString(String fieldType) {
        fieldType = fieldType.toLowerCase();
        switch (fieldType) {
            case "date" : return DATE;
            case "number" : return NUMBER;
            case "text" : return TEXT;
            case "string" :
            default: return STRING;
        }
    }
}
