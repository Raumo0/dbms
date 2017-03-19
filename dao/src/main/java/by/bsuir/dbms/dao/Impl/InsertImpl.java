package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Insert;
import by.bsuir.dbms.exceptions.DAOException;
import by.bsuir.dbms.tools.FileWorker;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class InsertImpl implements Insert {
    private FileWorker fileWorker;

    private InsertImpl() {
    }

    public static InsertImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean insertByValues(String table, String[] columns, String[] values) throws DAOException {
        CSVWriter writer;
        CSVReader reader;
        String[] tableCol;
        fileWorker = new FileWorker(table);
        if (!fileWorker.fileExists(table))
            throw new DAOException();
        try {
            reader = new CSVReader(new FileReader(table));
            tableCol = reader.readNext();
            if (tableCol == null)
                throw new DAOException();
            for (String col : columns){
                if (!Arrays.asList(tableCol).contains(col))
                    throw new DAOException();
            }
            writer = new CSVWriter(new FileWriter(table, true));
            if (values != null)
                writer.writeNext(values);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return true;
    }

    private static class SingletonHolder {
        private static final InsertImpl INSTANCE = new InsertImpl();
    }
}
