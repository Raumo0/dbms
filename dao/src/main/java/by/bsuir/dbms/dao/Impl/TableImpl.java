package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Table;
import by.bsuir.dbms.exceptions.DAOException;
import by.bsuir.dbms.tools.FileWorker;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TableImpl implements Table {
    private FileWorker fileWorker;

    private TableImpl() {}

    public static TableImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void create(String name) throws DAOException {
        this.create(name, null);
    }

    public void create(String name, String[] header) throws DAOException {
        this.create(name, header, null);
    }

    public void create(String name, String[] header, List<String[]> rows) throws DAOException {
        CSVWriter writer;
        fileWorker = new FileWorker(name);
        try {
            fileWorker.create(name);
            writer = new CSVWriter(new FileWriter(name));
            if (header == null)
                header = new String[]{};
            writer.writeNext(header);
            if (rows != null)
                writer.writeAll(rows);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    public void delete(String name) {
        fileWorker = new FileWorker(name);
        fileWorker.delete(name);
    }

    private static class SingletonHolder{
        private static final TableImpl INSTANCE = new TableImpl();
    }
}
