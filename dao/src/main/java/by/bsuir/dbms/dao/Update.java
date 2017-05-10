package by.bsuir.dbms.dao;

import by.bsuir.dbms.exceptions.DAOException;

public interface Update {
    void updateRow(String table, int id, String[] columns, String[] values) throws DAOException;
}
