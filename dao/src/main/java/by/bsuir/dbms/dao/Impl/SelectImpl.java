package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Select;
import by.bsuir.dbms.exceptions.DAOException;
import by.bsuir.dbms.tools.FileWorker;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectImpl implements Select {
    private FileWorker fileWorker;

    private SelectImpl() {
    }

    public static SelectImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<String[]> selectAllFromTable(String table) throws DAOException {
        CSVReader reader ;
        List<String[]> result = new ArrayList<String[]>();
        try {
            reader = new CSVReader(new FileReader(table));
            String[] line;
            while ((line = reader.readNext()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return result;
    }

    private static class SingletonHolder {
        private static final SelectImpl INSTANCE = new SelectImpl();
    }
}
