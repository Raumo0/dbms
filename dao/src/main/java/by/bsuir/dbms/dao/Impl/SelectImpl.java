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
        FileWorker fileWorker = null;
        List<String[]> result;
        try {
            fileWorker = new FileWorker(table, "r");
            result = fileWorker.readCSV();
        } catch (FileException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        finally {
            try {
                fileWorker.close();
            } catch (FileException e) {
                throw new DAOException(e);
            }
        }
        return result;
    }

    private static class SingletonHolder {
        private static final SelectImpl INSTANCE = new SelectImpl();
    }
}
