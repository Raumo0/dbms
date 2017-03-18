package by.bsuir.dbms.dao;

import java.util.List;

public interface Table {
    void create(String name, String[] header, List<String[]> rows);
    void delete(String name);
}
