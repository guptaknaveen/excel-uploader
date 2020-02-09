package com.s2p.utility.exceluploader.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableData extends CommonModel {
    private List<String> headers;
    private List<Map<String, String>> data;

    public TableData() {
        this.data = new ArrayList<>();
        this.headers = new ArrayList<>();
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }

    public void addHeader(String header) {
        this.headers.add(header);
    }

    public void addData(Map<String, String> dataRow) {
        this.data.add(dataRow);
    }
}
