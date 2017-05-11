package by.bsuir.dbms.dao;

import by.bsuir.dbms.exceptions.DAOException;

public interface Index {
    void createIndexByColumn(String table, String column) throws DAOException;
    void deleteIndexByColumn(String table, String column) throws DAOException;
    void updateIndexByColumn(String table, String column) throws DAOException;
}
