package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Index;
import by.bsuir.dbms.exceptions.DAOException;

public class IndexImpl implements Index {
    private static IndexImpl ourInstance = new IndexImpl();

    public static IndexImpl getInstance() {
        return ourInstance;
    }

    private IndexImpl() {
    }

    @Override
    public void createIndexByColumn(String table, String column) throws DAOException {
        throw new DAOException();
    }

    @Override
    public void deleteIndexByColumn(String table, String column) throws DAOException {
        throw new DAOException();
    }

    @Override
    public void updateIndexByColumn(String table, String column) throws DAOException {
        throw new DAOException();
    }
}
