package by.bsuir.dbms.dao;

import by.bsuir.dbms.exceptions.DAOException;

public interface Delete {
    void deleteLine(String table, int line) throws DAOException;
    void delete(String table, int offset, int size) throws DAOException;
}
