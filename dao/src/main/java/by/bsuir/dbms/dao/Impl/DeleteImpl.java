package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Delete;
import by.bsuir.dbms.exceptions.DAOException;

import java.util.List;

public class DeleteImpl implements Delete {
    private static DeleteImpl ourInstance = new DeleteImpl();

    public static DeleteImpl getInstance() {
        return ourInstance;
    }


    @Override
    public void deleteLine(String table, int line) throws DAOException {
        List<String[]> data;
        data = SelectImpl.getInstance().selectAllFromTable(table);
        data.remove(line);
        TableImpl.getInstance().create(table, null, data);
    }

    @Override
    public void delete(String table, int offset, int size) throws DAOException {
        throw new DAOException();
    }

    private DeleteImpl() {
    }
}
