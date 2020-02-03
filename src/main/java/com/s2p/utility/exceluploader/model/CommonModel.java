package com.s2p.utility.exceluploader.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Document
public class CommonModel implements Serializable {
    @Id
    protected String id;
    protected Map info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addInfo(Object key, Object value) {
        if (this.info == null) {
            this.info = new HashMap();
        }
        this.info.put(key, value);
    }

    public Object getInfo(Object key) {
        if (this.info == null) {
            return null;
        }
        return this.info.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonModel that = (CommonModel) o;
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info);
    }

    @Override
    public String toString() {
        return "CommonModel{" +
                "info=" + info +
                '}';
    }
}
