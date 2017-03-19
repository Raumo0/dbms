package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Select;
import by.bsuir.dbms.exceptions.DAOException;
import by.bsuir.dbms.exceptions.FileException;
import by.bsuir.dbms.tools.FileWorker;

import java.util.List;

public class SelectImpl implements Select {
    private SelectImpl() {
    }

    public static SelectImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<String[]> selectAllFromTable(String table) throws DAOException {
        FileWorker fileWorker = new FileWorker(table);
        List<String[]> result;
        try {
            result = fileWorker.readCSV();
        } catch (FileException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return result;
    }

    private static class SingletonHolder {
        private static final SelectImpl INSTANCE = new SelectImpl();
    }
}
