package by.bsuir.dbms.dao;

import by.bsuir.dbms.exceptions.DAOException;

import java.util.List;

public interface Select {
    List<String[]> selectAllFromTable(String table) throws DAOException;
    List<String[]> selectColumnsFromTable(String table, String[] columns) throws DAOException;
}
