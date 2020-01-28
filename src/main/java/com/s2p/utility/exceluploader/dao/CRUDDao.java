package com.s2p.utility.exceluploader.dao;

import java.util.List;

public interface CRUDDao<T>  {
    T saveOrUpdate(final T t);
    T delete(final int id);
    List<T> view();
    T view(final int id);
}
