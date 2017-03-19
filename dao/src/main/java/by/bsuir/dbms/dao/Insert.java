package by.bsuir.dbms.dao;

import by.bsuir.dbms.exceptions.DAOException;

public interface Insert {
    boolean insertByValues(String table, String[] columns, String[] values) throws DAOException;
}
