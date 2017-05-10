package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Table;
import by.bsuir.dbms.exceptions.DAOException;
import by.bsuir.dbms.exceptions.FileException;
import by.bsuir.dbms.tools.FileWorker;

import java.util.ArrayList;
import java.util.List;

public class TableImpl implements Table {
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
        FileWorker fileWorker = null;
        try {
            fileWorker = new FileWorker(name, "rw");
            List<String[]> lines = new ArrayList<>(rows);
            if (header != null && header.length != 0)
                lines.add(0, header);
            fileWorker.writeCSV(lines);
        } catch (FileException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        finally {
            try {
                fileWorker.close();
            } catch (FileException | NullPointerException e) {
                throw new DAOException(e);
            }
        }
    }

    public void delete(String name) {
        FileWorker.delete(name);
    }

    private static class SingletonHolder{
        private static final TableImpl INSTANCE = new TableImpl();
    }
}
