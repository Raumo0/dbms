package by.bsuir.dbms.dao;

import by.bsuir.dbms.exceptions.DAOException;

import java.util.List;

public interface Table {
    void create(String name) throws DAOException;
    void create(String name, String[] header) throws DAOException;
    void create(String name, String[] header, List<String[]> rows) throws DAOException;
    void delete(String name);
    boolean tableExists(String tableName);
}
