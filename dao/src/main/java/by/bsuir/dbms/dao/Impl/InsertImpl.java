package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Insert;
import by.bsuir.dbms.exceptions.DAOException;
import by.bsuir.dbms.exceptions.FileException;
import by.bsuir.dbms.tools.FileWorker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertImpl implements Insert {
    private InsertImpl() {
    }

    public static InsertImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean insertByValues(String table, String[] columns, String[] values) throws DAOException {
        String[] header;
        FileWorker fileWorker = null;
        try {
            fileWorker = new FileWorker(table, "rw");
            List<String> row = new ArrayList<>();
            if (!fileWorker.fileExists(table))
                throw new DAOException();
            header = fileWorker.readHeaderCSV();
            if (header == null)
                throw new DAOException();
            for (String col : columns) {
                if (!Arrays.asList(header).contains(col))
                    throw new DAOException();
            }
            int i = 0;
            for (String col : header) {
                if (!Arrays.asList(columns).contains(col))
                    row.add("");
                else {
                    row.add(values[i]);
                    i++;
                }
            }
            fileWorker.writeAppendCSV(row.toArray(new String[]{}));
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
        return true;
    }

    private static class SingletonHolder {
        private static final InsertImpl INSTANCE = new InsertImpl();
    }
}
