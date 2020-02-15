package com.s2p.utility.exceluploader.constant;

public enum ExcelType {
    XLS ("xls","2003"),
    XLSX ("xlxs", "2007");

    private String extension, label;

    ExcelType(String extension, String label) {
        this.extension = extension;
        this.label = label;
    }

    public String extension() {
        return this.extension;
    }

    public String label() {
        return this.label;
    }
}
