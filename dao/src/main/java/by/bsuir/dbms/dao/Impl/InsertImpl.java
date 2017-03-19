package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Insert;
import by.bsuir.dbms.exceptions.DAOException;
import by.bsuir.dbms.exceptions.FileException;
import by.bsuir.dbms.tools.FileWorker;

import java.util.Arrays;

public class InsertImpl implements Insert {
    private InsertImpl() {
    }

    public static InsertImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean insertByValues(String table, String[] columns, String[] values) throws DAOException {
        String[] tableCol;
        FileWorker fileWorker = new FileWorker(table);
        if (!fileWorker.fileExists(table))
            throw new DAOException();
        try {
            tableCol = fileWorker.readHeaderCSV();
            if (tableCol == null)
                throw new DAOException();
            for (String col : columns){
                if (!Arrays.asList(tableCol).contains(col))
                    throw new DAOException();
            }
            fileWorker.writeAppendCSV(values);
        } catch (FileException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return true;
    }

    private static class SingletonHolder {
        private static final InsertImpl INSTANCE = new InsertImpl();
    }
}
