package by.bsuir.dbms.dao;

import by.bsuir.dbms.exceptions.DAOException;

import java.util.List;

public interface Select {
    List<String[]> selectAllFromTable(String table) throws DAOException;
}
